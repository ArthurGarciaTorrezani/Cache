import java.util.HashSet;

public class Mapping {
    
    public long[] mapping(long vector[], long enderecos[]) {

        long sizeBitsMem = exponent(vector[0]);
        long sizeLine = 4 * vector[1];
        long numLine = vector[2];
        long numVias = vector[3];
        long numGroup = vector[2] / vector[3]; //Número de linhas / Número de linhas por conjunto
        long numBitsDataBloc = 0;
        long numBitsGroup = 0;
        long numBitsTag = 0;
        long cacheMiss = 0;
        long cacheHit = 0;

        numBitsGroup = exponent(numGroup);

        numBitsDataBloc = exponent(4 * vector[1]);

        if (numGroup == numLine) { //Direto
            long numBitsLinha = exponent(numLine);
            numBitsTag = sizeBitsMem - numBitsLinha - numBitsDataBloc;
        }

        if (numGroup < numLine && numLine != numVias) { //Associativo por conjunto
            numBitsTag = sizeBitsMem - numBitsDataBloc - numBitsGroup;
        }

        if (numGroup == 1) { //Completamente associativo
            numBitsTag = sizeBitsMem - numBitsDataBloc;
        }

        HashSet<Long> blocsInCache = new HashSet<Long>();
        for (long x : enderecos) {
            long numBloco = x / sizeLine;

            if (blocsInCache.contains(numBloco)) {
                cacheHit++;
            } else {
                blocsInCache.add(numBloco);
                cacheMiss++;
            }
        }

        long result[] = new long[5];

        result[0] = numBitsDataBloc;
        result[1] = numBitsGroup;
        result[2] = numBitsTag;
        result[3] = cacheMiss;
        result[4] = cacheHit;

        return result;
    }

    public long exponent(long num) {

        if (num == 1) {
  
           return 0;
        }
  
        return 1 + exponent(num / 2);
     }
  
}
