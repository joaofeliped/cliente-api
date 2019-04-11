package com.mirante.api.resource;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mirante.api.exceptionhandler.ClienteExceptionHandler.Erro;
import com.mirante.api.model.Cliente;
import com.mirante.api.model.exception.CaractereInvalidoException;
import com.mirante.api.service.ClienteService;

/**
 * Resource da entidade cliente.
 */
@RestController
@RequestMapping("/clientes")
public class ClienteResource {
	
	/**
	 * Serviço de clientes.
	 */
	@Autowired
	private ClienteService clienteService;
	
	/**
	 * Message source.
	 */
	@Autowired
	private MessageSource messageSource;
	
	/**
	 * Salva um cliente.
	 * 
	 * @param cliente
	 * @return ResponseEntity<?>
	 */
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CLIENTE') and #oauth2.hasScope('write')")
	public ResponseEntity<?> salvar(@Valid @RequestBody Cliente cliente) {
		this.clienteService.salvar(cliente);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	

	/**
	 * Edita um cliente.
	 * 
	 * @param codigo
	 * @param cliente
	 * @return ResponseEntity<?>
	 */
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CLIENTE') and #oauth2.hasScope('write')")
	public ResponseEntity<?> editar(@PathVariable("codigo") Long codigo, @Valid @RequestBody Cliente cliente) {
		Cliente clienteCadastrado = this.clienteService.buscarPorCodigo(codigo);
		
		if(Objects.isNull(clienteCadastrado)) {
			return ResponseEntity.notFound().build();
		}
		
		this.clienteService.atualizar(cliente);
		
		return ResponseEntity.ok().build();
	}
	
	/**
	 * Deleta um cliente.
	 * 
	 * @param codigo
	 * @return ResponseEntity<?>
	 */
	@DeleteMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_CLIENTE') and #oauth2.hasScope('write')")
	public ResponseEntity<?> deletar(@PathVariable("codigo") Long codigo) {
		Cliente cliente = this.clienteService.buscarPorCodigo(codigo);
		
		if(Objects.isNull(cliente)) {
			return ResponseEntity.notFound().build();
		}
		
		this.clienteService.deletar(codigo);
		
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Busca o cliente pelo código.
	 * 
	 * @param codigo
	 * @return ResponseEntity<Cliente>
	 */
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CLIENTE') and #oauth2.hasScope('read')")
	public ResponseEntity<?> buscarPorCodigo(@PathVariable("codigo") Long codigo) {
		Cliente cliente = this.clienteService.buscarPorCodigo(codigo);
		
		if(Objects.isNull(cliente)) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(cliente);
	}
	
	/**
	 * Retorna a lista de clientes.
	 * 
	 * @return ResponseEntity<List<Cliente>>
	 */
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CLIENTE') and #oauth2.hasScope('read')")
	public ResponseEntity<List<Cliente>> buscar() {
		List<Cliente> clientes = this.clienteService.buscarTodos();
		
		return ResponseEntity.ok(clientes);
	}
	
	@ExceptionHandler({CaractereInvalidoException.class})
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(CaractereInvalidoException ex) {
		String mensagemUsuario = messageSource.getMessage("caractere-invalido", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		
		return ResponseEntity.badRequest().body(erros);	
	}
}
