package com.alessiopriore.view;

import com.alessiopriore.model.TipoTelModel;

public class TipoTelView {
	private TipoTelModel tipoTel;
	
	public TipoTelView(){};
	
	public TipoTelView(TipoTelModel tipoTel) {
		this.tipoTel = tipoTel;
	}

	public void printInfo(){
		System.out.println(tipoTel.toString());
	}
	
	public TipoTelModel getTipoTel() {
		return tipoTel;
	}

	public void setTipoTel(TipoTelModel tipoTelModel) {
		this.tipoTel = tipoTel;
	}
}
