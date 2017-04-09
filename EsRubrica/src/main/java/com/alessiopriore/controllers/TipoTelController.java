package com.alessiopriore.controllers;

import com.alessiopriore.model.TipoTelModel;
import com.alessiopriore.view.TipoTelView;

public class TipoTelController {
	TipoTelModel tipoTelModel;
	TipoTelView  tipoTelView;
	
	public TipoTelController(TipoTelModel tipoTelModel, TipoTelView tipoTelView) {
		this.tipoTelModel = tipoTelModel;
		this.tipoTelView = tipoTelView;
	}
	
	public void updateView(TipoTelModel tipoTelModel){
		tipoTelView.setTipoTel(tipoTelModel);
	}
	
	public void updateModel(TipoTelView tipoTelView){
		
		this.tipoTelModel = tipoTelView.getTipoTel();		
	}
	
	public TipoTelModel getTipoTelModel() {
		return tipoTelModel;
	}
	public void setTipoTelModel(TipoTelModel tipoTelModel) {
		this.tipoTelModel = tipoTelModel;
	}
	public TipoTelView getTipoTelView() {
		return tipoTelView;
	}
	public void setTipoTelView(TipoTelView tipoTelView) {
		this.tipoTelView = tipoTelView;
	}
}
