package com.learningspring.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.learningspring.constants.PedidoStatusConstants;

@Entity
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	@Column(name = "instante", nullable = false)
	private Instant instante;

	@ManyToOne
	@JoinColumn(name = "clienteid")
	private Usuario cliente;

	private Integer pedidoStatus;

	@OneToMany(mappedBy = "id.pedido")
	private Set<ItemPedido> itensPedido = new HashSet<>();

	// Mapeio a relação 1 para 1 com o mesmo id
	@OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
	private Pagamento pagamento;

	public Pedido() {
		super();
	}

	public Pedido(Long id, Instant instante, PedidoStatusConstants pedidoStatus, Usuario cliente) {
		super();
		this.id = id;
		this.instante = instante;
		this.cliente = cliente;
		setPedidoStatus(pedidoStatus);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getInstante() {
		return instante;
	}

	public void setInstante(Instant instante) {
		this.instante = instante;
	}

	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}

	public PedidoStatusConstants getPedidoStatus() {
		return PedidoStatusConstants.convertToPedidoStatus(pedidoStatus);
	}

	public void setPedidoStatus(PedidoStatusConstants pedidoStatus) {
		if (pedidoStatus != null) {
			this.pedidoStatus = pedidoStatus.getCodigo();
		}
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Set<ItemPedido> getItensPedido() {
		return itensPedido;
	}

	public Double getTotalPedido() {
		double totalPedido = getItensPedido().stream() //
				.mapToDouble(e -> e.getSubTotal())  //
				.reduce(1, (val1, val2) -> val1 + val2);
		return totalPedido;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}