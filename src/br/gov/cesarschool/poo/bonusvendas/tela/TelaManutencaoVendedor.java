package br.gov.cesarschool.poo.bonusvendas.tela;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import br.gov.cesarschool.poo.bonusvendas.negocio.*;
import br.gov.cesarschool.poo.bonusvendas.dao.*;
import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Endereco;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Sexo;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Label;
public class TelaManutencaoVendedor {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 * 
	 */
	VendedorDAO vendedorDAO = new VendedorDAO();
	LancamentoBonusDAO lancamentoBonusDAO= new LancamentoBonusDAO();
	private VendedorMediator mediator = VendedorMediator.getInstancia();
	
	private Text cpf;
	private Text nome;
	private Text nascimento;
	private Text renda;
	private Text logradouro;
	private Text numero;
	private Text complemento;
	private Text CEP;
	private Text cidade;
	private Text estado;
	private Text txtNome;
	private Text txtCpf;
	private Text txtLogradouro;
	private Text txtDataDeNascimento;
	private Text txtRenda;
	private Text txtNumero;
	private Text txtComplemento;
	private Text txtCep;
	private Text txtCidade;
	private Text txtEstado;

	    private String descricao;
	    private Text txtPais;
	    private Label lblNome;
	    private Label lblNascimento;
	    private Label lblCpf;
	    private Label lblRenda;
	    private Label lblLogradouro;
	    private Label lblNumero;
	    private Label lblComplemento;
	    private Label lblCep;
	    private Label lblCidade;
	    private Label lblEstado;
	    private Label lblPas;

	    private void Sexo(String descricao) {
	        this.descricao = descricao;
	    }

	    public String getDescricao() {
	        return descricao;
	    }

	    @Override
	    public String toString() {
	        return descricao;
	    }
	
	public static void main(String[] args) {
		try {
			TelaManutencaoVendedor window = new TelaManutencaoVendedor();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(622, 605);
		shell.setText("SWT Application");
		shell.setLayout(null);
		
		Button btnLimpada = new Button(shell, SWT.NONE);
		btnLimpada.setTouchEnabled(true);
		btnLimpada.setBounds(448, 172, 105, 35);
		btnLimpada.setText("Limpada");
		
		txtNome = new Text(shell, SWT.BORDER);
		txtNome.setText("");
		txtNome.setTouchEnabled(true);
		txtNome.setEnabled(true);
		txtNome.setBounds(180, 59, 189, 31);
		txtCpf = new Text(shell, SWT.BORDER);
		txtCpf.setText("");
		txtCpf.setBounds(180, 137, 189, 31);
		
		txtLogradouro = new Text(shell, SWT.BORDER);
		txtLogradouro.setText("");
		txtLogradouro.setBounds(180, 211, 189, 31);
		
		Button btnM = new Button(shell, SWT.RADIO);
		btnM.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnM.setBounds(461, 62, 133, 25);
		btnM.setText("M");
		
		txtDataDeNascimento = new Text(shell, SWT.BORDER);
		txtDataDeNascimento.setText("");
		txtDataDeNascimento.setBounds(180, 96, 189, 31);
		
		txtRenda = new Text(shell, SWT.BORDER);
		txtRenda.setText("");
		txtRenda.setBounds(180, 174, 189, 31);
		
		txtNumero = new Text(shell, SWT.BORDER);
		txtNumero.setText("");
		txtNumero.setBounds(180, 248, 189, 31);
		txtNumero.addVerifyListener(event -> {
		    String currentText = ((Text) event.widget).getText();
		    String newText = currentText.substring(0, event.start) + event.text + currentText.substring(event.end);		
		    if (newText.length() > 7) {
		        event.doit = false; 
		    }
		});
		txtComplemento = new Text(shell, SWT.BORDER);
		txtComplemento.setText("");
		txtComplemento.setBounds(180, 285, 189, 31);
		
		txtCep = new Text(shell, SWT.BORDER);
		txtCep.setText("");
		txtCep.setBounds(180, 320, 189, 31);
		
		Button btnF = new Button(shell, SWT.RADIO);
		btnF.setBounds(461, 99, 133, 25);
		btnF.setText("F");
		
		txtCidade = new Text(shell, SWT.BORDER);
		txtCidade.setText("");
		txtCidade.setBounds(180, 357, 189, 31);
		
		txtEstado = new Text(shell, SWT.BORDER);
		txtEstado.setText("");
		txtEstado.setBounds(180, 394, 189, 31);
		
		Button btnarmazenar = new Button(shell, SWT.NONE);
		btnarmazenar.setBounds(448, 131, 105, 35);
		btnarmazenar.setText("armazenar");
		
		txtPais = new Text(shell, SWT.BORDER);
		txtPais.setText("");
		txtPais.setBounds(179, 428, 190, 31);
		
		Label lblSexo = new Label(shell, SWT.NONE);
		lblSexo.setBounds(395, 62, 52, 25);
		lblSexo.setText("Sexo:");
		
		lblNome = new Label(shell, SWT.NONE);
		lblNome.setBounds(111, 62, 63, 25);
		lblNome.setText("Nome:");
		
		lblNascimento = new Label(shell, SWT.NONE);
		lblNascimento.setBounds(78, 99, 96, 25);
		lblNascimento.setText("Nascimento:");
		
		lblCpf = new Label(shell, SWT.NONE);
		lblCpf.setBounds(124, 140, 43, 25);
		lblCpf.setText("CPF:");
		
		lblRenda = new Label(shell, SWT.NONE);
		lblRenda.setBounds(111, 177, 63, 25);
		lblRenda.setText("Renda:");
		
		lblLogradouro = new Label(shell, SWT.NONE);
		lblLogradouro.setBounds(73, 214, 101, 25);
		lblLogradouro.setText("Logradouro:");
		
		lblNumero = new Label(shell, SWT.NONE);
		lblNumero.setBounds(93, 251, 81, 25);
		lblNumero.setText("Numero:");
		
		lblComplemento = new Label(shell, SWT.NONE);
		lblComplemento.setBounds(55, 288, 119, 25);
		lblComplemento.setText("Complemento:");
		
		lblCep = new Label(shell, SWT.NONE);
		lblCep.setBounds(122, 323, 52, 25);
		lblCep.setText("CEP:");
		
		lblCidade = new Label(shell, SWT.NONE);
		lblCidade.setBounds(111, 360, 63, 25);
		lblCidade.setText("Cidade:");
		
		lblEstado = new Label(shell, SWT.NONE);
		lblEstado.setBounds(111, 397, 63, 25);
		lblEstado.setText("Estado:");
		
		lblPas = new Label(shell, SWT.NONE);
		lblPas.setBounds(121, 431, 43, 25);
		lblPas.setText("País:");
		btnLimpada.addSelectionListener(new SelectionAdapter() {
		    @Override
		    public void widgetSelected(SelectionEvent e) {
		        // Lógica para limpar os campos de texto
		        txtCpf.setText("");
		        txtNome.setText("");
		        txtPais.setText("");
		        txtEstado.setText("");
		        txtCidade.setText("");
		        txtCep.setText("");
		        txtLogradouro.setText("");		        
				txtDataDeNascimento.setText("");								
				txtRenda.setText("");				
				txtNumero.setText("");			
				txtComplemento.setText("");
				btnM.setSelection(false);
		        btnF.setSelection(false);
		    }
		});
		// ...
		btnarmazenar.addSelectionListener(new SelectionAdapter() {
		    @Override
		    public void widgetSelected(SelectionEvent e) {
		        String nome = txtNome.getText();
		        String cpf = txtCpf.getText();
		      

		   
		        Sexo sexo = btnM.getSelection() ? Sexo.MASCULINO : Sexo.FEMININO;

		        String dataDeNascimentoStr = txtDataDeNascimento.getText();
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		        try {
		            LocalDate dataDeNascimento = LocalDate.parse(dataDeNascimentoStr, formatter);
		        } catch (DateTimeParseException excecao) {
		            // Mostrar mensagem de erro ao usuário
		            System.err.println("Data de nascimento inválida, por favor digite no formato dd/MM/yyyy" + excecao.getMessage());
		            return;
		        }

		        LocalDate dataDeNascimento = null;
		        try {
		            dataDeNascimento = LocalDate.parse(dataDeNascimentoStr, formatter);
		        } catch (DateTimeParseException excecao) {
		            System.err.println("Erro ao analisar a data: " + excecao.getMessage());
		        }
		        String renda = txtRenda.getText();
		        if (!renda.isEmpty()) {
		            try {
		                double rendaValue = Double.parseDouble(renda);
		            } catch (NumberFormatException excecao) {
		                System.err.println("Error parsing renda: " + excecao.getMessage());
		            }
		        } else {
		            System.err.println("Renda está vazio");
		        }

		        String logradouro = txtLogradouro.getText();
		        String numeroStr = txtNumero.getText();
		        int numero = 0; 
		        try {
		            numero = Integer.parseInt(numeroStr);
		        } catch (NumberFormatException excecao) {
		            System.err.println("Erro: Inteiro errado para 'numero'");
		            return;
		        }
		        String complemento = txtComplemento.getText();
		        String cep = txtCep.getText();
		        if (cep.length() != 8 || !cep.matches("\\d{8}")) {
		            System.err.println("CEP inválido. Deve conter 8 dígitos.");
		            return;
		        }
		        String cidade = txtCidade.getText();
		        String estado = txtEstado.getText();
		        String pais = txtPais.getText();		        
		        Endereco endereco = new Endereco(logradouro, numero, complemento, cep, cidade, estado,pais);
		        Vendedor vendedor = new Vendedor(cpf, nome, sexo, dataDeNascimento, Double.parseDouble(renda), endereco);
		        boolean saved = vendedorDAO.incluir(vendedor);

		        if (saved) {
		            System.out.println("Vendedor salvo.");
		        } else {
		            System.out.println("Vendedor com mesmo CPF existe.");
		        }
		    }
		});

	}
}
