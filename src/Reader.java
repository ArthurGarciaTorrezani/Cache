import java.io.*;

public class Reader {
    Mapping mapping = new Mapping();

    public void ler(String txt) {
        int cont = 0;
        String adress[];
        long vector[] = new long[4];
        long result[] = new long[5];
        try {
            BufferedReader bf = new BufferedReader(new FileReader(txt));
            String line = bf.readLine();
            while (line != null) { 
                if (cont != 4) {
                    vector[cont] = Long.parseLong(line);
                } else {
                    adress = line.split(" ");
                    long intAdress[] = new long[adress.length];

                    int cont2 = 0;
                    for (String x : adress) {
                        intAdress[cont2] = Long.parseLong(x);
                        cont2++;
                    }
                    
                    result = mapping.mapping(vector, intAdress);
                } 
                
                cont++;
    
                line = bf.readLine();
            }

            writeResult(txt, result);
            bf.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void writeResult(String inputFilePath, long[] result) {

        File inputFile = new File(inputFilePath);
        String outputFileName = inputFile.getName().replace(".txt", "-RESULTADO.txt");
        String outputFilePath = inputFile.getParent() + File.separator + outputFileName;
        
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));

                for (long i : result) {
                    writer.write(Long.toString(i));
                    writer.newLine();
                }
            
            writer.close();
            System.out.println("Arquivo " + outputFileName + " criado com sucesso!");
    
        } catch (IOException e) {
            System.out.println("Erro ao escrever o arquivo: " + e.getMessage());
        }
    }
}
