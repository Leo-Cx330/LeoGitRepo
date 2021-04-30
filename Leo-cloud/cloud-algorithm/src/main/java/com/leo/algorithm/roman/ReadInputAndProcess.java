package com.leo.algorithm.roman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Copyright zm
 * @FileName: Leo-cloud
 * @Author: lihao
 * @Date: 2020/11/8 7:12 下午
 * @Description:
 * @author: lihao
 */
public class ReadInputAndProcess {

    public static final String NO_IDEA = "I have no idea what you are talking about";

    private static Map<String, RomanNumbers.Roman> interGalacticRomanMapping = new HashMap<>();
    private static Map<String, Double> objectSoldPerUnitValue = new HashMap<>();

    /**
     * reads and process the input file
     * @param fileName
     */
    public void readFileAndProcess(String fileName) throws Exception{

        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;

            while((line = br.readLine()) != null)
            {
                processInput(line.trim());
            }

        } catch (IOException e) {
            System.out.println("File not found exception " + e);
        }
    }

    /**
     * Parses the input line by line and decides the type of request and appropriately forwards the request
     * @param line
     * @throws Exception
     */
    private void processInput(String line) throws Exception{
        //split by whitespace
        line = line.toLowerCase();
        String[] inputs = line.split("\\s+");
        List<String> inputQuestions = DisplayOutput.inputQuestions;
        List<String> outputValues = DisplayOutput.outputValues;

        if(line.startsWith("how many credits is")){
            inputQuestions.add(line);
            outputValues.add(String.valueOf(generateCreditValue(Arrays.copyOfRange(inputs, 4, inputs.length-1))));
        }
        else if(line.startsWith("how much is")){
            inputQuestions.add(line);
            outputValues.add(String.valueOf(generateGalacticUnitToNumericValue(Arrays.copyOfRange(inputs, 3, inputs.length-1))));
        }
        else if(line.contains("is") && !line.contains("credits")) {
            mapInterGalacticToRomanUnits(inputs);
        } else if(line.contains("is") && line.contains("credits")) {
            generateObjectSoldPerUnitMap(inputs);
        } else{
            inputQuestions.add(line);
            outputValues.add(NO_IDEA);
        }

    }

    /**
     * Receives inputs of the form "glob is I"
     * maps the alien language to the RomanType
     * @param arr
     */
    private void mapInterGalacticToRomanUnits(String[] arr){
        try{
            interGalacticRomanMapping.put(arr[0], RomanNumbers.Roman.valueOf(arr[2]));
        }
        catch(IllegalArgumentException e){
            System.out.println("This type of Roman is not defined, exiting the program " + e);
        }
    }

    /**
     * Receives input of the form "glob glob Silver is 34 Credits"
     * Creates a map of the object sold and (value/unit)
     * returns -1 in case of a validation error
     * @param arr
     * @return
     * @throws Exception
     */
    private int generateObjectSoldPerUnitMap(String[] arr) throws Exception{
        StringBuilder romanNumeral = new StringBuilder();
        int i;
        for(i = 0; i<arr.length; i++){
            RomanNumbers.Roman roman = interGalacticRomanMapping.get(arr[i]);
            if(roman != null){
                romanNumeral.append(roman.getDisplayValue());
            }
            else {
                break;
            }
        }

        int value = RomanValidator.validateRoman(romanNumeral.toString());
        if(value == -1) {
            return -1;
        }
        String objectName = arr[i];
        int credit = Integer.parseInt(arr[i + 2]);

        objectSoldPerUnitValue.put(objectName, (double)credit/value);
        return 1;
    }

    /**
     * Receives input of the form "pish tegj glob glob" for questions like "how much is pish tegj glob glob ?"
     * returns -1 in case of validation exception
     * @param arr
     * @return
     * @throws Exception
     */
    private int generateGalacticUnitToNumericValue(String[] arr){

        try {
            String romanNumeral = generateRomanFromGalacticUnit(arr);

            if(romanNumeral == null) {
                return -1;
            }

            return  RomanValidator.validateRoman(romanNumeral.toString());
        } catch (Exception e) {
            return -1;
        }

    }


    /**
     * Receives input of the form "glob prok Silver" for questions like "how many Credits is glob prok Silver ?"
     * returns -1 in case of validation exception
     * @param arr
     * @return
     * @throws Exception
     */
    private Double generateCreditValue(String[] arr){
        int currentValue = generateGalacticUnitToNumericValue(Arrays.copyOfRange(arr, 0, arr.length-1));

        if(currentValue == -1) {
            return (double)-1;
        }

        return currentValue * objectSoldPerUnitValue.get(arr[arr.length-1]);
    }

    /**
     * a utils method to generate Roman string from Alien input
     * @param arr
     * @return
     */
    private String generateRomanFromGalacticUnit(String[] arr){
        StringBuilder romanNumeral = new StringBuilder();
        int i;
        for(i = 0; i< arr.length; i++){
            RomanNumbers.Roman roman = interGalacticRomanMapping.get(arr[i]);
            if(roman != null){
                romanNumeral.append(roman.getDisplayValue());
            }
            else {
                break;
            }
        }

        if(i != arr.length) {
            return null;
        }

        return romanNumeral.toString();
    }
}
