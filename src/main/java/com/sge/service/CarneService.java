package com.sge.service;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import com.sge.dao.CarneDAO;
import com.sge.model.Carne;
import com.sge.util.Transactional;

//@ManagedBean
//@SessionScoped

@Named
@javax.faces.view.ViewScoped
public class CarneService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// ############################# CARNE AUTOMATICO #############################
	
	@Inject
	private CarneDAO carneDao;
	private List<Carne>carnes;
	private Carne carne = new Carne();
	@Inject
	private Carne carneSelecionado = new Carne();
	
	private boolean isentarJuros = false;
		
	@Transactional
	public String salvar(){
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (carne.getId() == null) {
				System.out.print("######################## Carne Salvo: " + carne.getAluno().getNome());
				carne.setNumParcela(getNumeroParcela());
				carne.setValorMensalidade(carne.getValorMensalidade());
				calculaMensalidade();
				carneDao.save(carne);

				return "/addCarne.xhtml?faces-redirect=true id="+carne.getAluno().getId();
			} else {
				carne.setNumParcela(getNumeroParcela());
				carne.setValorMensalidade(carne.getValorMensalidade());
				calculaMensalidade();
				carneDao.update(carne);
				return "/addCarne.xhtml?faces-redirect=true id="+carne.getAluno().getId();
			}
		} catch (Exception e) {			
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
			return "/addCarne.xhtml?faces-redirect=true id="+carne.getAluno().getId();
		}
	}
	
	
	@Transactional
	public String gerarCarne() {
		Integer anoLetivo =  Integer.parseInt(this.carne.getAluno().getAnoLetivo());		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		for (int i = 1; i <13; i++) {
			Carne carne = new Carne();
			carne.setAluno(this.carne.getAluno());
			
			String dataString = "05-"+ i +"-"+ anoLetivo;
			Date vencimento = null;
			try {
				vencimento = sdf.parse(dataString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			carne.setNumParcela(i);
			carne.setVencimentoParcela(vencimento);
			carne.setValorFinal(this.carne.getValorMensalidade());
			carne.setPago(false);			
			carneDao.save(carne);
			System.out.println("Carnes Gerados -  Mes " + i );
		}
		return "/addCarne.xhtml?faces-redirect=true id="+carne.getAluno().getId();
	}	
		
	@Transactional
	public void findAll(){
		this.carnes = carneDao.findAll();
	}
	
	public void calculaMensalidade(){
		Float desconto = 0f;
		//Float juros = null;
		// EXTRAIR DIA DO PAGAMENTO E VERIFICAR 
		//SE É MENOR || IGUAL QUE DIA 5 DESCONTO DE 4O REAIS
		//SE É MAIOR QUE DIA 5  E MENOR Q DIA 11 DESCONTO DE 2O REAIS
		
		// Após Vencimento serão cobrados juros de	1% ao mês
		// Acrescido de multa de 2% ao mês
		//GregorianCalendar calendar = new GregorianCalendar();
		
		@SuppressWarnings("deprecation")
		Integer diaPagamento = carne.getDataPagamento().getDate();
		
		@SuppressWarnings("deprecation")
		Integer mesPagamento = carne.getDataPagamento().getMonth();		
		
		@SuppressWarnings("deprecation")
		Integer mesVencimento = carne.getVencimentoParcela().getMonth();																	
		
		@SuppressWarnings("deprecation")
		Integer anoPagamento = carne.getDataPagamento().getYear();		
		
		@SuppressWarnings("deprecation")
		Integer anoVencimento = carne.getVencimentoParcela().getYear();
		
		
		// CALCULO DE JUROS e MULTAS
		Float juros = 00.00f;
		if (verificavencimento(carne.getDataPagamento(),carne.getVencimentoParcela()) == true) {	
			
			if (anoPagamento == anoVencimento) {
				juros = 0.01f * (carne.getValorMensalidade()) * (mesPagamento - mesVencimento);
				juros = 0.02f * (carne.getValorMensalidade()) * (mesPagamento - mesVencimento);
				System.out.println("================================ Primeiro IF ===========================");
			} else if (anoPagamento > anoVencimento) {
				juros = 0.01f * (carne.getValorMensalidade()) * ((mesPagamento + 12) - mesVencimento);
				juros = 0.02f * (carne.getValorMensalidade()) * ((mesPagamento + 12) - mesVencimento);
				System.out.println("================================ Primeiro IF MES ANO CORRIGIDO ===========================");							
			}			
			
			if ((mesPagamento - mesVencimento == 0) && (anoPagamento >= anoVencimento)) {
				juros = 0.01f * (carne.getValorMensalidade());
				juros = 0.02f * (carne.getValorMensalidade());
				System.out.println("================================ Segundo IF ===========================");
			}
			if (juros < 0) {
				System.out.println("=====================--------------- Menor que ZERO -----------------=========================");
				juros = juros * -1;	
			}
			
			System.out.println("Carne Vencido - ");
			System.out.println("Data de Vencimento:" + carne.getVencimentoParcela());
			System.out.println("n/ Data de Pagamento:" + carne.getDataPagamento());			 
			
			System.out.println("Juros: " + juros);
		} else {
			juros = 00.00f;
			System.out.println("Juros: " + juros);
		}
		if (isIsentarJuros() == true){
			juros = 0.00f;
		}
		
		// VERIFICA VENCIMENTO E CALCULA DESCONTOS
		if ((diaPagamento <= 5 && mesPagamento <= mesVencimento && anoPagamento == anoVencimento) // Se pagamento for antes do dia 5 do mes anterior
				|| 
				((mesPagamento < mesVencimento) && (anoPagamento == anoVencimento)) //Se pagemento for mes antereiror do mesmo ano
			)
		{	
			System.out.println(" ======================= DESCONTO MES - 40 DILMAS ==============================");
			desconto = 40.00f;
		} 
		else if (anoPagamento < anoVencimento) {
			System.out.println(" ======================= DESCONTO ANO - 40 DILMAS ==============================");
			desconto = 40.00f;
		}
		
		else if (diaPagamento <= 10 && (mesPagamento == mesVencimento) && anoPagamento == anoVencimento ) {
			System.out.println(" ======================= DESCONTO 20 DILMAS ==============================");
			desconto = 20f;
		} 	
		
		// ATRIBUI OS VALORES AO CARNE
		carne.setJuros(juros);
		carne.setValorDesconto(desconto);				
		System.out.println("Dia do Pagamento: " + diaPagamento);
		System.out.println("Dia do Vencimento: " + mesVencimento);
		System.out.println("Valor do desconto: " + desconto);
		carne.setValorFinal((carne.getValorMensalidade() - desconto) + juros);
	}
	
	public boolean verificavencimento(Date emissao, Date vencimento){       
	    boolean vencido;  
	    if (emissao.before(vencimento)) {
	    	System.out.println("Vencido");
	        vencido = false;  
	    }  
	    else if (emissao.after(vencimento)) {
	    	System.out.println("Não Vencido");
	        vencido = true;
	    }
	    else  {
	        vencido = false;
	        System.out.println("Else Vencido");
	    }
	    return vencido;  
	}  
	
	public Integer getNumeroParcela(){
		
		@SuppressWarnings("deprecation")
		Integer numeroParcela = carne.getVencimentoParcela().getMonth() + 1;
		System.out.println("Mes: " + numeroParcela);
		return numeroParcela;
	}
	
	public List<Carne> getCarnes() {
		return carnes;
	}
	public void setCarnes(List<Carne> carnes) {
		this.carnes = carnes;
	}
	public Carne getCarne() {
		return carne;
	}
	public void setCarne(Carne carne) {
		this.carne = carne;
	}
	public Carne getCarneSelecionado() {
		return carneSelecionado;
	}
	public void setCarneSelecionado(Carne carneSelecionado) {
		this.carneSelecionado = carneSelecionado;
	}
	
	public void onRowSelect(SelectEvent slc) {
		carneSelecionado = (Carne) slc.getObject();
		carne = new Carne();
		carne = carneSelecionado;
		System.out.println("Carne selecionado: " + carne.getId());
	}
	@Transactional
	public String remover() {	
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			carneDao.delete(carneSelecionado, carneSelecionado.getId());
			context.addMessage(null, new FacesMessage("Carne Excluido"));
			return "/addCarne.xhtml?faces-redirect=true id="+carne.getAluno().getId();	
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("Deu RUIM");
			return "/addCarne.xhtml?faces-redirect=true id="+carne.getAluno().getId();
		}
		
	}

	public boolean isIsentarJuros() {
		return isentarJuros;
	}

	public void setIsentarJuros(boolean isentarJuros) {
		this.isentarJuros = isentarJuros;
	}
}
