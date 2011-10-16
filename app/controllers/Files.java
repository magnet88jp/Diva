package controllers;

import java.io.*;
import java.util.*;
import play.*;
import play.mvc.*;
import play.libs.IO;
import play.vfs.VirtualFile;
import jobs.*;
import play.libs.F.Promise;
//import play.templates.BaseTemplate;
//import play.templates.GroovyTemplate;
import play.templates.Template;
import play.templates.TemplateLoader;

public class Files extends Controller {
  public static void index() {
    try {
//      File ifile = new File("doc/test.html");
      BufferedReader br = new BufferedReader(new FileReader("doc/test.html"));
//      if(!ifile.exists()) {
//        notFound("Page not found");
//      }
      String line;
      while ((line = br.readLine()) != null) {
        System.out.println(line);
      } 
      br.close();

//      File ofile = new File("doc/out.html");
     PrintWriter pr = new PrintWriter(new BufferedWriter(new FileWriter("doc/out.html")));

//      byte[] textile;
//      textile = new byte[256];
////      textile = IO.readContent(ifile);
//      textile[0] = 'a';
//      textile[1] = 'b';
//      textile[2] = 'b';
//      IO.write(textile, ofile);

     pr.println("123");
     pr.println("456");
     pr.println("789");

     pr.close();
      
    } catch (FileNotFoundException e){
    } catch (IOException e){
    } finally {
      Logger.info("log finally.");
    }
 
    render();

  }

  public static void job() {
    // read template
    // write template
    Promise<String> pdf = new MyJob().now();
    String str = await(pdf);

    for(int i = 0; i <= 10; i++) {
      Logger.info("%d", i);
//      Logger.info("log finally.");
      await("1s");
    }

    render(str);
  }

  public static void write() {
    // read template
    //File file = new File("doc/simple-txt.tmpl");
    try {
      BufferedReader br = new BufferedReader(new FileReader("doc/test.html"));
      String line;
      StringBuffer sb = new StringBuffer();
      while ((line = br.readLine()) != null) {
        sb.append(line);
      } 
      br.close();

      Logger.info(sb.toString());
//      GroovyTemplate gt = new GroovyTemplate(sb.toString());
//      GroovyTemplate gt = new GroovyTemplate("doc/test.html", sb.toString());

      Template template = TemplateLoader.load("doc/test.html");
//      GroovyTemplate gt = new GroovyTemplate(template.source, sb.toString());
//      BaseTemplate gt = new BaseTemplate(sb.toString());

      // map
//      Map map = new HashMap();
//      map.put("name", "nishino");
//      map.put("tel", "01-2345-6789");

      Map<String, Object> templateBinding = new HashMap<String, Object>(16);
//        for (Object o : args) {
//            List<String> names = LocalVariablesNamesTracer.getAllLocalVariableNames(o);
//            for (String name : names) {
//                templateBinding.put(name, o);
//            }
//        }
      templateBinding.put("name", "nishino");
//      templateBinding.put("tel", "01-2345-6789");
      //  renderTemplate(templateName, templateBinding);


      //gt.render(map);
//      String str2 = gt.render(templateBinding);
      String str2 = template.render(templateBinding);
      Logger.info(str2);
 

    } catch (FileNotFoundException e){
    } catch (IOException e){
    } finally {
      Logger.info("write finally.");
    }


//    binding = ["favlang": "Groovy"]
//    engine = new SimpleTemplateEngine()
//    template = engine.createTemplate(fle).make(binding)
//    println template.toString()

    // write template

    render();

  }

}
