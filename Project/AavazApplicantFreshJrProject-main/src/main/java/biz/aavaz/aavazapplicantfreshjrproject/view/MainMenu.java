package biz.aavaz.aavazapplicantfreshjrproject.view;

import biz.aavaz.aavazapplicantfreshjrproject.controller.SkillController;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class MainMenu {

    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {

        boolean isContinue = true;

        do {
            paintMenu();

            switch (processInput()) {

                case 1:// add skill
                    addSkill();
                    break;
                case 2:
                    printSkills();
                    break;
                case 3:
                    addUser();
                    break;
                case 4:
                    getUser();
                    break;
                case 5:// exit
                    System.out.println("Good bye");
                    isContinue = false;
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }

        } while (isContinue);
    }

    public static void paintMenu() {
        System.out.println("=========================");
        System.out.println("1. Add a Skill by reading a JSON file");
        System.out.println("2. Print all skills in database");
        System.out.println("3. Add a User by reading a json file");
        System.out.println("4. Get all Users by skill name");
        System.out.println("5. Exit");
    }

    public static int processInput() {

        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    private static void addSkill() {
        //Mycode 
  
        String defaultSkillsFolder = "src/main/resources/skills/";
        String filePath = "C:\\Users\\Lenovo\\Downloads\\Project\\AavazApplicantFreshJrProject-main\\src\\main\\resources\\skills";
        SkillController skillController = new SkillController();

        System.out.println("Default skills folder -> [" + defaultSkillsFolder + " ]");
        System.out.println("1. Use default folder");
        System.out.println("2. Specify full path");

        Scanner userScanner = new Scanner(System.in);

        if (userScanner.nextInt() == 2) {
            System.out.println("Enter full path including filename & extention:");
            filePath = userScanner.next();
        } else {
            System.out.println("Enter filename with extention ( eq. english.json) :");
            filePath = defaultSkillsFolder + userScanner.next();
        }

        try {
            skillController.addSkill(filePath);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("There was an error while processing");
        }
    }

    private static void printSkills() {
        JSONParser parser = new JSONParser();
        try
        {
            //to access json file 
        Object obj = parser.parse(new FileReader("C:\\Users\\Lenovo\\Downloads\\Project\\AavazApplicantFreshJrProject-main\\src\\main\\resources\\skills"));
        JSONObject jsonObject  = (JSONObject)obj;
        JSONArray userSkills = (JSONArray)jsonObject.get("skills");  //to get skills of user
        String FirstName = (String)jsonObject.get("first_name");  
        String LastName = (String)jsonObject.get("last_name");
        String name = FirstName.concat(LastName);  //taking first and last name into full name 
        System.out.println("Name" + name);
        System.out.println("Skills:\n");
        Iterator iterator = userSkills.iterator();  //displaying all skills of particular user
        while(iterator.hasNext()){
        System.out.println(iterator.next());
        }
        }catch (ParseException e) {
        e.printStackTrace();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }       
        System.out.println("!!!!! To be implemented by candidate");
    }
    
    private static void addUser() throws IOException
    {
        Scanner s = new Scanner(System.in);
        int n;
        JSONObject obj = new JSONObject();
      JSONArray skills = new JSONArray();
      System.out.println("Enter number of skils\n");
      n=s.nextInt();
      String skillName = "";
        int level;
      for(int i=0;i<n;i++)
      { 
      skillName = s.nextLine();
      level = s.nextInt();
      skills.add("name: "+ skillName );
      skills.add("level: "+ level );
      }
      obj.put("skills:", skills);
      try (FileWriter file = new FileWriter("C:\\Users\\Lenovo\\Downloads\\Project\\AavazApplicantFreshJrProject-main\\src\\main\\resources\\skills\\userSkill.json")) {
         file.write(obj.toJSONString());
         System.out.println("JSON Object write to a File successfully");
         System.out.println("JSON Object: " + obj);
      }
    }
    
    private static void getUser() throws FileNotFoundException, IOException, ParseException
    {
        Scanner sc = new Scanner(System.in);
        
        JSONParser jsonParser = new JSONParser();
        //Parsing the contents of the JSON file
        try{
        Object obj = jsonParser.parse(new FileReader("C:\\Users\\Lenovo\\Downloads\\Project\\AavazApplicantFreshJrProject-main\\src\\main\\resources\\skills"));
        JSONObject jsonObject  = (JSONObject)obj;
        JSONArray userSkills = (JSONArray)jsonObject.get("skills");  //to get skills of user
         
        String temp="";
        System.out.println("Enter Skills");
        temp = sc.nextLine();
        String str = (String)jsonObject.get("userSkiils");
        if(temp==str)
        {
            String FirstName = (String)jsonObject.get("first_name");
            String LastName = (String)jsonObject.get("last_name");
            String name = FirstName.concat(LastName);  //taking first and last name into full name
            System.out.println("Name" + name);
        } else 
        {
            System.out.println("Unablt to locate Skills of given user");
            
        }
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (ParseException e) {
         e.printStackTrace();
      }
        
        
    }

   }
