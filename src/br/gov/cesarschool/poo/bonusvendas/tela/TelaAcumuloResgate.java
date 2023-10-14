package br.gov.cesarschool.poo.bonusvendas.tela;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import br.gov.cesarschool.poo.bonusvendas.negocio.AcumuloResgateMediator;
import br.gov.cesarschool.poo.bonusvendas.negocio.VendedorMediator;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class TelaAcumuloResgate {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	private AcumuloResgateMediator mediator = AcumuloResgateMediator.getInstancia();
	private Text text;
	private Text text_1;
	public static void main(String[] args) {
		try {
			TelaAcumuloResgate window = new TelaAcumuloResgate();
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
		shell.setSize(565, 300);
		shell.setText("SWT Application");
		
		Label lblBonus = new Label(shell, SWT.NONE);
		lblBonus.setBounds(65, 23, 81, 25);
		lblBonus.setText("Bonus");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(65, 54, 80, 31);
		
		Label lblOperao = new Label(shell, SWT.NONE);
		lblOperao.setBounds(10, 91, 81, 25);
		lblOperao.setText("Operação");
		
		Button btnCreditar = new Button(shell, SWT.RADIO);
		btnCreditar.setBounds(13, 122, 133, 25);
		btnCreditar.setText("creditar");
		
		Button btnBuscar = new Button(shell, SWT.NONE);
		btnBuscar.setBounds(65, 182, 105, 35);
		btnBuscar.setText("Buscar");
		
		Label lblSaldoAtual = new Label(shell, SWT.NONE);
		lblSaldoAtual.setBounds(204, 23, 111, 25);
		lblSaldoAtual.setText("Saldo Atual");
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(210, 54, 105, 31);
		
		Label lblTipoDeResgate = new Label(shell, SWT.NONE);
		lblTipoDeResgate.setBounds(323, 23, 133, 25);
		lblTipoDeResgate.setText("Tipo de resgate");
		
		Combo combo = new Combo(shell, SWT.NONE);
		combo.setBounds(352, 54, 104, 33);
		
		Button btnAcumular = new Button(shell, SWT.NONE);
		btnAcumular.setBounds(182, 182, 105, 35);
		btnAcumular.setText("Acumular");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setBounds(293, 182, 105, 35);
		btnNewButton.setText("Resgatar");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnNewButton_1.setBounds(404, 182, 105, 35);
		btnNewButton_1.setText("Voltar");

	}
}
