package GenerateView;

import java.util.*;

public class Main {
     public static void main(String[] args) throws Exception {
          String lanquage = "cs";
          Personne p1 = new Personne("PERS001", "Tafitasoa", "Tanjonirina", 18);
          Personne p2 = new Personne("PERS002", "Huhu", "hoho", 23);
          Personne p3 = new Personne("PERS003", "Andria", "Rakoto", 14);
          List<Personne> listPersone = new ArrayList<>();
          listPersone.add(p1);
          listPersone.add(p2);
          listPersone.add(p3);

          new GenerateList().generate(listPersone, lanquage);
          // insert
          Personne personne = new Personne();
          new GenerateInsert().generate(personne, lanquage);
     }
}
