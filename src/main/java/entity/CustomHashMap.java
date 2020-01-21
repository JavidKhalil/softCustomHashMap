package entity;

import exception.ForbidenValueProvidedException;

public class CustomHashMap<K, V> {

    private K key;
    private V value;
    private CustomHashMapEntry[] bucketCollection;
    private int defaultCapacity = 100;

    public CustomHashMap() {
        this.bucketCollection = new CustomHashMapEntry[defaultCapacity];
    }

    public CustomHashMap(int defaultCapacity) {
        this.bucketCollection = new CustomHashMapEntry[defaultCapacity];
    }

    public void put(K key, V value){

        /*
        verify provided key
        */
        if(key == null){
            throw new ForbidenValueProvidedException();
        }

        /*
        first we need to get hash of the key
        */
        int hashOfKey = getHashOfKey(key);

        /*
        we create new entry, which (as we plan)
        we`ll add to our bukcetCollection
         */
        CustomHashMapEntry candidateForAddToBucket = new CustomHashMapEntry(key, value);

        /*
        if our bucketCollection already has entry with
        identical hashCode, which we call current entry

        if this current entry is null
        we just add here our new candidate entry
        */
        if(bucketCollection[hashOfKey] == null){
            bucketCollection[hashOfKey] = candidateForAddToBucket;

            /*
            if currentEntry is not null we check equality of keys two entries
            */

        } else {
            if(key.equals(bucketCollection[hashOfKey].key)){

                /*
                if they equal we replace old element with new
                */

                bucketCollection[hashOfKey] = candidateForAddToBucket;
            } else {

                /*
                if keys are not equal we look throw linkedList and
                find 'null' element and add our new entry there
                */

                CustomHashMapEntry emptyEntryInLinkedList = bucketCollection[hashOfKey].nextEntry;
                while(emptyEntryInLinkedList != null){
                    emptyEntryInLinkedList = emptyEntryInLinkedList.nextEntry;
                }
                emptyEntryInLinkedList = candidateForAddToBucket;
            }
        }
    }

    public V get(K key){

        if(key == null){
            throw new ForbidenValueProvidedException();
        }

        int hash = getHashOfKey(key);
        CustomHashMapEntry lookedEntry = bucketCollection[hash];
        while(lookedEntry != null){
            if(key.equals(lookedEntry.key)){
                return (V) lookedEntry.value;
            } else {
                if(lookedEntry.nextEntry != null){
                    lookedEntry = lookedEntry.nextEntry;
                }
            }
        }
        return null;
    }

    public int size(){

        /*
        int value we will operate with for calculate size
        */
        int size = 0;

        /*
        entry we will use for check if the element in some position is exists
        */
        CustomHashMapEntry currentElement = null;

        /*
        loop throw our bucket and check existence of the entries
        */
        for(int index = 0; index < defaultCapacity; index++){
            currentElement = bucketCollection[index];

            /*
            if the element is exists we increment our variable
            */
            if(currentElement != null){
                ++size;

                /*
                also for correctness we should loop throw linkedList
                */
                while(currentElement.nextEntry != null){
                    ++size;
                    currentElement = currentElement.nextEntry;
                }
            }
        }
        return size;
    }

    /*
    we get hashCode of the key and calculate modulus for defaultCapacity of hashMap
    */
    private int getHashOfKey(K key){
        return key.hashCode()%defaultCapacity;
    }

    private static class CustomHashMapEntry<K, V>{

        private K key;
        private V value;
        private CustomHashMapEntry<K, V> nextEntry;

        public CustomHashMapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}
