 
package airtiesapp;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;


 class Airtiesapp {
 
 private static String  dotype;
 private static String  mappath; 
 private static String  configpth; 
 private static String  setget; 
 private static String  enter; 
 private static String  mapid; 
 private static HashMap<String,uniqfile> uniqmap;
 private static configfile config;
 private static String  uniqidi;
 
    public static void main(String[] args) throws IOException, InterruptedException {
        if(args.length > 0){
            
            try {
                for(int i=0;i<args.length;i++){
                    
                    String arg =args[i];
                    String item1=arg.split("=")[0];
                    String item2=arg.split("=")[1];
                    
                    if(item1.equals("-do")){
                        String val =item2;
                        dotype = item2.split(":")[0];
                        enter =item2.split(":")[1];
                    }else if(item1.equals("-map")){
                        mappath =item2;
                    }else if(item1.equals("-id")){
                        mapid =item2;
                    }else if(item1.equals("-config")){
                        configpth =item2;
                    }
                }
                File file =new File(mappath);
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                documentBuilderFactory.setNamespaceAware(true);
                documentBuilderFactory.setIgnoringElementContentWhitespace(true);
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(file);
                document.getDocumentElement().normalize();
            
                  uniqmap =new HashMap<>();
                  NodeList nodlist =document.getElementsByTagName("Uniq");
                  for(int e=0; e <nodlist.getLength(); e++){
                      
                      String id ="";
                      String information ="";
                      String pagelink ="";
                      String partialobject ="";
                      String pageobject  ="";
                      String objectype ="";
                      String savebutton ="";
                    
                           
                   Element element =(Element) nodlist.item(e);
                   id = element.getAttribute("id");
                   information = element.getElementsByTagName("Information").item(0).getTextContent();
                   pagelink = element.getElementsByTagName("PageLink").item(0).getTextContent();
                   partialobject= element.getElementsByTagName("PartialObject").item(0).getTextContent();
                   pageobject = element.getElementsByTagName("PageObject").item(0).getTextContent();
                   objectype = element.getElementsByTagName("ObjectType").item(0).getTextContent();
                   savebutton = element.getElementsByTagName("SaveButton").item(0).getTextContent();
                   uniqfile uniq = new uniqfile(id,information ,pagelink,partialobject,pageobject ,objectype,savebutton);
                  
                   uniqmap.put(id,uniq);
                  }
             
            } catch (Exception ex) {
                ex.printStackTrace();
            }
             String IP ="";
             String USERNAME ="";
             String PASSWORD ="";
             
            List<String> lines;
            
            lines =Files.readAllLines(Paths.get(configpth));
            for(String ln: lines){
                String firstv  =ln.split("=")[0];
                String secv =ln.split("=")[1];
                if(firstv.equals("IP")){
                  IP =secv;
                }else if(firstv.equals("USERNAME")){
                  USERNAME =secv;
                }else if(firstv.equals("PASSWORD")){
                  PASSWORD =secv;
                }
            }
          config = new configfile(IP,USERNAME,PASSWORD);

          System.setProperty("webdriver.gecko.driver" , "C:\\Users\\User\\Desktop\\geckodriver.exe");
          WebDriver driver  = new FirefoxDriver(); 
          StringBuilder buıld =new StringBuilder("http://");
          buıld.append(config.getIP());
          String url=buıld.toString();
          driver.navigate().to(url);
          WebDriverWait wait = new WebDriverWait(driver, 10);
          wait.until(ExpectedConditions.titleContains("AirTies"));
          Thread.sleep(15000);
         // for(int a=0;a <uniqidi.length();a++){
           uniqfile u = uniqmap.get(mapid);
                  StringBuilder nexturl = new StringBuilder(url);
                  nexturl.append("/");
                  nexturl.append(u.getPageLink());
                  String pagelink =nexturl.toString();
                  driver.navigate().to(pagelink);
                  Thread.sleep(15000);
                  driver.findElement(By.id(u.getPartialObject())).click();
                  if(dotype.equals("set")){
                    if(u.getObjectType().equals("txtbox")){
                      driver.findElement(By.id(u.getPageObject())).clear();
                      driver.findElement(By.id(u.getPageObject())).sendKeys(enter);
                      
                    }else if(u.getObjectType().equals("combobox")){
                       Select combo = new Select(driver.findElement(By.id(u.getPageObject())));
                       combo.deselectByIndex(Integer.parseInt(enter));
                    }
                    driver.findElement(By.id(u.getSaveButton())).click();
                  }else if(dotype.equals("get")){
                    String gvalue = "";
                    
                    if(u.getObjectType().equals("txtbox")){
                      
                      WebElement textbox= driver.findElement(By.id(u.getPageObject()));
                       gvalue = textbox.getAttribute("value");
                      
                    }else if(u.getObjectType().equals("combobox")){
                       Select combo = new Select(driver.findElement(By.id(u.getPageObject())));
                       gvalue = combo.getFirstSelectedOption().getText();
                    }
                    System.out.println(gvalue);
                 }
         // }
          
        }else{
         System.out.println("no args");
        }
    }
  
}
