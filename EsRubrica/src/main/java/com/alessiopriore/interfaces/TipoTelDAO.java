package com.alessiopriore.interfaces;

import com.alessiopriore.model.TipoTelModel;

public interface TipoTelDAO {
	public TipoTelModel getTipoTelInfo(String modello);
	public boolean updateTipoTelInfo(String modello, String marca);
	public boolean insertTipoTel(TipoTelModel utenteModel);
}
