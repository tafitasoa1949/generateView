package GenerateView;

public class AttributeInfo {
     private String nom;
     private Class<?> type;

     public AttributeInfo(String nom, Class<?> type) {
          this.nom = nom;
          this.type = type;
     }

     public String getNom() {
          return nom;
     }

     public Class<?> getType() {
          return type;
     }
}
