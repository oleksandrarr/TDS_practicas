package dao;

/** 
 * Factoria concreta DAO para el Servidor de Persistencia de la asignatura TDS.
 * 
 */

public final class TDSFactoriaDAO extends FactoriaDAO {
	
	public TDSFactoriaDAO() {	}
	
	@Override
	public TDSUsuarioDAO getUsuarioDAO() {	
		return new TDSUsuarioDAO(); 
	}
	
	@Override
	public TDSContactoDAO getContactoDAO() {	
		return new TDSContactoDAO(); 
	}
	
	@Override
	public TDSMensajeDAO getMensajeDAO() {	
		return new TDSMensajeDAO(); 
	}

}
