
import java.io.*;
import java.util.List;

public class GrepMain {
     // [-r] return ture if line contain reg
    public Boolean checkReg (String reg,String line){
        return line.matches(reg);
    }
    //-c is "" return ture if line contains word
    public Boolean checkWord (String word,String line){
        return line.contains(word);
    }
    public GrepMain Grep (List<String> commandline) {
        //print information of mistake if commandline have
        if (commandline.size()>3 || commandline.size() < 2){
            System.out.println("what input in commandline don not obey format, check it please");
        }
        // save information from commandline to command, word, fileNames
        String command = (commandline.size() == 2)? "":commandline.get(0);
        String word = (commandline.size() == 2)? commandline.get(0):commandline.get(1);
        String fileNames = (commandline.size() == 2)? commandline.get(1):commandline.get(2);
        try {
            //open file if fail print file not found
            BufferedReader readerTxt = new BufferedReader(new InputStreamReader(
                    new FileInputStream(fileNames)));
            //[-v] [-i] [-r]
            for (String line = readerTxt.readLine(); line != null; line = readerTxt.readLine()) {
                if (command.contains("[-v]")){
                    if (command.contains("[-r]")){
                        if (command.contains("[-i]")){
                            if (!checkReg(word.toLowerCase(),line.toLowerCase())){
                                System.out.println(line);
                            }
                        }else {
                            if (!checkReg(word,line)){
                                System.out.println(line);
                            }
                        }
                    }else {
                        if (command.contains("[-i]")){
                            if (!checkWord(word.toLowerCase(),line.toLowerCase())){
                                System.out.println(line);
                            }
                        }else {
                            if (!checkWord(word,line)){
                                System.out.println(line);
                            }
                        }
                    }
                }else {
                    if (command.contains("[-r]")){
                        if (command.contains("[-i]")){
                            if (checkReg(word.toLowerCase(),line.toLowerCase())){
                                System.out.println(line);
                            }
                        }else {
                            if (checkReg(word,line)){
                                System.out.println(line);
                            }
                        }
                    }else {
                        if (command.contains("[-i]")){
                            if (checkWord(word.toLowerCase(),line.toLowerCase())){
                                System.out.println(line);
                            }
                        }else {
                            if (checkWord(word,line)){
                                System.out.println(line);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("file not found");
        }
        return null;
    }

}
