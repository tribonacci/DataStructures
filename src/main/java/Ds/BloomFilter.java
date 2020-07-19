package Ds;

public class BloomFilter {
    Integer size;
    Integer noOfHashFunctions;
    Boolean[] storage;
    HashAlgo[] hashAlgos;

    public BloomFilter(Integer size, Integer noOfHashFunctions){
        this.size = size;
        this.noOfHashFunctions = noOfHashFunctions;
        storage = new Boolean[size];
        hashAlgos = new HashAlgo[noOfHashFunctions];

        for(int i=0; i<size ; i++){
            storage[i] = false;
        }
    }

    public boolean isElementPresent(Object item){
        for(int i=0; i<noOfHashFunctions ; i++){
            Integer pos = hashAlgos[i].generateHash(size , item);
            if (!storage[pos])
                return false;
        }
        return true;
    }

    public void insert(Object item){
        for(int i=0; i<noOfHashFunctions ; i++){
            Integer pos = hashAlgos[i].generateHash(size , item);
            storage[pos] = true;
        }
    }
}

interface HashAlgo{

    // should output an Integer between 0 to (outputSize-1)
    Integer generateHash(Integer outputSize, Object item);
}
