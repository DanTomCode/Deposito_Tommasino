public class Incapsulamento {


    public static void main(String[] args) {
       
        Prodotto prodotto = new Prodotto();

        prodotto.setNome("Matita");
        prodotto.setPrezzo(1.50);

        System.out.println("Nome: " + prodotto.getNome());
        System.out.println("Prezzo: " + prodotto.getPrezzo() + " euro");
    }
}

class Prodotto {

 private String nome;
 private double prezzo; 

    public void setNome(String nome) {

      this.nome = nome; 


    }


   public String getNome() {

    return nome; 

}

public void setPrezzo(double prezzo) {
 if (prezzo > 0) {
    this.prezzo = prezzo;

} else {

    System.out.println("il prezzo deve essere maggiore di zero");
}

  }

public double getPrezzo() {
  return prezzo;
  


}










}