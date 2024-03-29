public class Main {
    public static void main(String[] args) {
        int numSets = 100;
        InputDataGenerator inputDataGenerator = new InputDataGenerator();
        inputDataGenerator.generateInputData(numSets, 100, 10000);
        OutputData outputData = new OutputData();
        outputData.outputData(numSets);
    }
}
