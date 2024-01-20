package GenerateView;

import java.util.*;
import java.io.*;

public class GenerateList {
     private void generateCssList(String nom) throws Exception {
          try {
               String cssTemplate = "template/css/list.css";
               String css = Code.readBodyFile(cssTemplate);
               String fichierCss = nom + "List.css";
               try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichierCss))) {
                    writer.write(css);
               }
          } catch (Exception e) {
               System.out.println(e.getMessage());
          }
     }

     private String generateAttribut(Object object, String nom, String language) throws Exception {
          String template = "";
          try {
               String[] attributes = Code.getAttributes(object);
               String nomFichierTemplate = "template/list.templ";
               template = Code.readBodyFile(nomFichierTemplate);
               template = template.replace("#title#", "Liste " + nom + "s");
               if (language.equals("cs")) {
                    template = template.replace("#css#", "@Url.Content(" + nom + "List.css" + ")");
               } else {
                    template = template.replace("#css#", nom + "List.css");
               }
               String attributesContent = String.join("</th><th>", attributes);
               template = template.replace("#attributes#", attributesContent);

          } catch (Exception e) {
               System.out.println(e.getMessage());
          }
          return template;
     }

     private String generateData(String template, List<?> list) throws Exception {
          StringBuilder dataContent = new StringBuilder();
          try {
               for (Object item : list) {
                    Object[] values = Code.getAttributeValues(item);
                    StringJoiner joiner = new StringJoiner("</td><td>", "<tr><td>", "</td></tr>\n");

                    for (Object value : values) {
                         joiner.add(String.valueOf(value));
                    }

                    dataContent.append(joiner.toString());
               }
               template = template.replace("#data#", dataContent.toString());
          } catch (Exception e) {
               System.out.println(e.getMessage());
          }
          return template;
     }

     public void generate(List<?> list, String language) throws Exception {
          try {
               String vue = Code.changeMaj1erLettre(list.get(0).getClass().getSimpleName());
               String template = this.generateAttribut(list.get(0), vue, language);
               template = this.generateData(template, list);
               String nomVue = "list" + vue + ".html";
               if (language.equals("java")) {
                    nomVue = "list" + vue + ".jsp";
               } else if (language.equals("cs")) {
                    nomVue = "list" + vue + ".cshtml";
               }

               try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomVue))) {
                    writer.write(template);
               }
               // css
               this.generateCssList(vue);
          } catch (Exception e) {
               System.out.println(e.getMessage());
          }
     }
}
