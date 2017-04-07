package com.alessiopriore.view;

public class TipoTelView {
	TipoTelView tipoTelView;

	public TipoTelView(TipoTelView tipoTelView) {
		this.tipoTelView = tipoTelView;
	}

	public void printInfo(){
		System.out.println(tipoTelView.toString());
	}
	
	public TipoTelView getTipoTelView() {
		return tipoTelView;
	}

	public void setTipoTelView(TipoTelView tipoTelView) {
		this.tipoTelView = tipoTelView;
	}
}
