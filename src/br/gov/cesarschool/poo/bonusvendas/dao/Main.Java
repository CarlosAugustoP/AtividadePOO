public class Main {

    public static void main(String[] args) {
        // Create an instance of CaixaDeBonusDAO
        CaixaDeBonusDAO caixaDeBonusDAO = new CaixaDeBonusDAO();

        // Create a CaixaDeBonus object to save
        CaixaDeBonus caixaDeBonus = new CaixaDeBonus();
        caixaDeBonus.setSomeProperty("Some Value"); // Set properties as needed

        // Save the CaixaDeBonus object
        String chave = "unique_key"; // Provide a unique key
        caixaDeBonusDAO.salvarCaixaDeBonus(caixaDeBonus, chave);

        // Retrieve the saved CaixaDeBonus object
        CaixaDeBonus savedCaixaDeBonus = caixaDeBonusDAO.retrieveCaixaDeBonus(chave);

        // Check if the savedCaixaDeBonus matches the original one
        if (caixaDeBonus.equals(savedCaixaDeBonus)) {
            System.out.println("CaixaDeBonus saved and retrieved successfully!");
        } else {
            System.out.println("CaixaDeBonus was not saved correctly.");
        }
    }
}
