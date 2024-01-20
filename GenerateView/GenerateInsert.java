package GenerateView;

import java.util.HashMap;
import java.io.*;

public class GenerateInsert {
     private HashMap<String, String> typeInputJava = new HashMap();
     private HashMap<String, String> typeInputCs = new HashMap();

     public String getTypeInputJava(String typeKey) {
          return typeInputJava.get(typeKey);
     }

     public String getTypeInputCs(String typeKey) {
          return typeInputCs.get(typeKey);
     }

     public GenerateInsert() {
          this.typeInputJava.put("String", "text");
          this.typeInputJava.put("double", "number");
          this.typeInputJava.put("Date", "date");
          this.typeInputJava.put("Timestamp", "datetime-local");
          this.typeInputJava.put("int", "number");
          this.typeInputJava.put("double", "number");

          this.typeInputCs.put("String", "text");
          this.typeInputCs.put("double", "number");
          this.typeInputCs.put("Datetime", "datetime-local");
          this.typeInputCs.put("int", "number");
          this.typeInputCs.put("double", "number");
     }

     public String generateInput(String nom, String type) {
          return "<label for=\"" + nom + "\">" + Code.changeMaj1erLettre(nom) + " :</label>\n" +
                    "<input type=\"" + type + "\" id=\"" + nom + "\" name=\"" + nom + "\" required></br>\n";
     }

     private void generateCssInsert(String nom) throws Exception {
          try {
               String cssTemplate = "template/css/insert.css";
               String css = Code.readBodyFile(cssTemplate);
               String fichierCss = nom + "Insert.css";
               try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichierCss))) {
                    writer.write(css);
               }
          } catch (Exception e) {
               System.out.println(e.getMessage());
          }
     }

     public void generate(Object object, String language) throws Exception {
          String inputs = "";
          try {
               String nomFichierTemplate = "template/insert.templ";
               String template = Code.readBodyFile(nomFichierTemplate);
               AttributeInfo[] attributes = Code.getAttributeInfo(object);
               String vue = Code.changeMaj1erLettre(object.getClass().getSimpleName());
               String nomVue = "ajouter" + vue + ".html";
               if (language.equals("java")) {
                    template = template.replace("#css#", vue + "Insert.css");
                    for (int i = 0; i < attributes.length; i++) {
                         String typeInput = this.getTypeInputJava(attributes[i].getType().getSimpleName().toString());
                         inputs += generateInput(attributes[i].getNom(), typeInput);
                    }
                    nomVue = "ajouter" + vue + ".jsp";
               } else if (language.equals("cs")) {
                    template = template.replace("#css#", "@Url.Content(" + vue + "Insert.css" + ")");
                    for (int i = 0; i < attributes.length; i++) {
                         String typeInput = this.getTypeInputCs(attributes[i].getType().getSimpleName().toString());
                         inputs += generateInput(attributes[i].getNom(), typeInput);
                    }
                    nomVue = "ajouter" + vue + ".cshtml";
               }
               template = template.replace("#title#", "Insertion " + vue);
               template = template.replace("#objet#", vue);
               template = template.replace("#attributes#", inputs);
               try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomVue))) {
                    writer.write(template);
               }
               // css jj
               this.generateCssInsert(vue);
          } catch (Exception e) {
               System.out.println(e.getMessage());
          }
     }
}
