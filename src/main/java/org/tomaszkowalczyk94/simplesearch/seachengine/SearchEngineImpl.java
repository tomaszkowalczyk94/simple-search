package org.tomaszkowalczyk94.simplesearch.seachengine;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.tomaszkowalczyk94.simplesearch.memory.Memory;
import org.tomaszkowalczyk94.simplesearch.memory.Searchable;
import org.tomaszkowalczyk94.simplesearch.memory.Word;

import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class SearchEngineImpl implements SearchEngine{

    WordComparator wordComparator;

    private final static double MIN_PERCENT_TO_INCLUDED_IN_RESULTS = 0.1;

    @Override
    public List<SearchResult> search(List<String> findBy, Memory memory) {

        return memory
                .getSearchables()
                .values()
                .stream()
                .map(searchable -> search(findBy, searchable))
                .filter(searchResult -> searchResult.getPercent() > MIN_PERCENT_TO_INCLUDED_IN_RESULTS)
                .collect(Collectors.toList());
    }

    private SearchResult search(List<String> findBy, Searchable searchable) {
        ListIterator<Word> iterator = searchable.getWords().listIterator();

        double maxPercentOfSimilarity = 0.0;

        while (iterator.hasNext()) {
            LvlOfSimilarity compare = compare(findBy, iterator);

            if(maxPercentOfSimilarity < compare.getPercent()) {
                maxPercentOfSimilarity = compare.getPercent();
            }

            iterator.next();
        }
        return new SearchResult(maxPercentOfSimilarity, searchable.getName());
    }

    private LvlOfSimilarity compare(List<String> findBy, ListIterator<Word> iterator) {

        int countOfIncrementIterator = 0;

        double sumOfPercentSimilarity = 0.0;

        for (String searchingWord: findBy) {
            if(!iterator.hasNext()) {
                break;
            }

            Word wordInMemory = iterator.next();

            LvlOfSimilarity lvlOfSimilarity = wordComparator.countLevelOfSimilarity(searchingWord, wordInMemory.toString());
            sumOfPercentSimilarity += lvlOfSimilarity.getPercent();

            countOfIncrementIterator++;
        }

        undoIterator(iterator, countOfIncrementIterator);

        if(countOfIncrementIterator == 0) {
            return LvlOfSimilarity.DIFFERENT;
        }

        return new LvlOfSimilarity(sumOfPercentSimilarity/countOfIncrementIterator);
    }

    private void undoIterator(ListIterator<Word> iterator, int howManyTimesMakeUndo) {
        for( int i = 0; i<howManyTimesMakeUndo; i++) {
            iterator.previous();
        }
    }
}
