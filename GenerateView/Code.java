package GenerateView;

import java.lang.reflect.Field;
import java.io.*;

public class Code {
     public static String changeMaj1erLettre(String lettre) {
          return lettre.substring(0, 1).toUpperCase() + lettre.substring(1);
     }

     public static String readBodyFile(String nomFichier) throws Exception {
          StringBuilder contenu = new StringBuilder();
          try (BufferedReader reader = new BufferedReader(new FileReader(nomFichier))) {
               String ligne;
               while ((ligne = reader.readLine()) != null) {
                    contenu.append(ligne).append("\n");
               }
          } catch (Exception e) {
               e.printStackTrace();
          }
          return contenu.toString();
     }

     public static String[] getAttributes(Object obj) throws IllegalAccessException {
          Class<?> clazz = obj.getClass();
          Field[] fields = clazz.getDeclaredFields();
          String[] attributeNames = new String[fields.length];

          for (int i = 0; i < fields.length; i++) {
               fields[i].setAccessible(true);
               attributeNames[i] = fields[i].getName();
          }

          return attributeNames;
     }

     public static AttributeInfo[] getAttributeInfo(Object obj) throws IllegalAccessException {
          Class<?> clazz = obj.getClass();
          Field[] fields = clazz.getDeclaredFields();
          AttributeInfo[] attributeInfoArray = new AttributeInfo[fields.length];

          for (int i = 0; i < fields.length; i++) {
               fields[i].setAccessible(true);
               String attributeName = fields[i].getName();
               Class<?> attributeType = fields[i].getType();
               AttributeInfo attributeInfo = new AttributeInfo(attributeName, attributeType);
               attributeInfoArray[i] = attributeInfo;
          }

          return attributeInfoArray;
     }

     public static Object[] getAttributeValues(Object obj) throws IllegalAccessException {
          Class<?> clazz = obj.getClass();
          Field[] fields = clazz.getDeclaredFields();
          Object[] attributeValues = new Object[fields.length];

          for (int i = 0; i < fields.length; i++) {
               fields[i].setAccessible(true);
               attributeValues[i] = fields[i].get(obj);
          }

          return attributeValues;
     }
}
