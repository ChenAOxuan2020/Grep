
import java.io.*;

public class GrepMain {
    public Boolean checkReg (String reg,String line){
        return line.matches(reg);
    }
    public Boolean checkWord (String word,String line){
        return line.indexOf(word) != -1;
    }
    public GrepMain Grep (String command, String word, String fileName) {
        try {
            BufferedReader readerTxt = new BufferedReader(new InputStreamReader(
                    new FileInputStream(fileName)));
            for (String line = readerTxt.readLine(); line != null; line = readerTxt.readLine()) {
                if (command.indexOf("[-v]") != -1){
                    if (command.indexOf("[-r]") != -1){
                        if (command.indexOf("[-i]") != -1){
                            if (!checkReg(word.toLowerCase(),line.toLowerCase())){
                                System.out.println(line);
                            }
                        }else {
                            if (!checkReg(word,line)){
                                System.out.println(line);
                            }
                        }
                    }else {
                        if (command.indexOf("[-i]")!= -1){
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
                    if (command.indexOf("[-r]") != -1){
                        if (command.indexOf("[-i]") != -1){
                            if (checkReg(word.toLowerCase(),line.toLowerCase())){
                                System.out.println(line);
                            }
                        }else {
                            if (checkReg(word,line)){
                                System.out.println(line);
                            }
                        }
                    }else {
                        if (command.indexOf("[-i]") != -1){
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
        }
        return null;
    }

}
