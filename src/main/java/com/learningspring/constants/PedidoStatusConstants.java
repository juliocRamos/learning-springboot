package com.learningspring.constants;

public enum PedidoStatusConstants {
	AGUARDANDO_PAGAMENTO(1), PAGO(2), ENVIADO(3), CANCELADO(5), ENTREGUE(4);

	private int codigo;

	private PedidoStatusConstants(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}

	public static PedidoStatusConstants convertToPedidoStatus(int codigo) {
		for (PedidoStatusConstants status : PedidoStatusConstants.values()) {
			if (status.getCodigo() == codigo) {
				return status;
			}
		}
		throw new IllegalArgumentException("Código de Status do Pedido é inválido");
	}
}