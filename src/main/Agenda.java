package main;

import java.util.Scanner;

public class Agenda {

	static Contato[] contatos = new Contato[3];
	static Scanner input = new Scanner(System.in);
	static int numContatos = 0;

	public static void main(String[] args) {
		while (true) {
			System.out.println("[1] Adicionar contato");
			System.out.println("[2] Buscar contato");
			System.out.println("[3] Editar contato");
			System.out.println("[4] Excluir contato");
			System.out.println("[5] Listar contatos");
			System.out.println("[6] Sair");
			int opcao = input.nextInt();

			/* Limpa o buffer do teclado */
			input.nextLine();

			switch (opcao) {
			/* Adicionar contato */
			case 1:
				if (numContatos == contatos.length) {
					System.out.println(
							"Infelizmente, a Agenda esta lotada! Exclua um contato para poder adicionar outro.");
					break;
				}

				System.out.print("Informe nome: ");
				String nome = input.nextLine();

				System.out.println("Informe o telefone:");
				String telefone = input.nextLine();

				// Adiciona o contato ao primeiro índice nulo
				for (int i = 0; i < contatos.length; i++) {
					if (contatos[i] == null) {
						contatos[i] = new Contato(nome, telefone);
						break;
					}
				}

				contatos[numContatos] = new Contato(nome, telefone);

				System.out.println("Contato cadastrado com sucesso!\n");

				numContatos++;

				break;

			/* Buscar contato */
			case 2:
				if (isVazia())
					break;

				Contato resultado[] = new Contato[numContatos];
				int encontrados = 0;

				encontrados = buscarContato(resultado);

				/* Imprimir resultado da busca */

				if (encontrados == 0) {
					System.out.println("Nao existem contatos com as informacoes especificadas.");
					break;
				}

				if (encontrados > 1) {
					System.out.println("Encontrados %s contatos:".formatted(encontrados));
				}

				for (int i = 0; i < encontrados; i++) {
					System.out.println("--------------------------------");
					System.out.println("Contato: " + (encontrados > 1 ? (i + 1) : ""));
					System.out.println("Nome: " + resultado[i].getNome());
					System.out.println("Telefone: " + resultado[i].getTelefone());
				}

				System.out.println("--------------------------------");

				break;

			/* Editar contato */
			case 3:
				if (isVazia())
					break;

				break;

			/* Excluir contato */
			case 4:
				if (isVazia())
					break;
				System.out.println("------------------------------------------");
				System.out.println("Excluir contato");
				listarContatos();
				System.out.println("Insira o índice do contato a ser excluído:");

				break;

			/* Listar contatos */
			case 5:
				if (isVazia())
					break;

				listarContatos();

				break;
			/* Sair */
			case 6:
				input.close();
				return;

			default:
				System.out.println("Opcao invalida!\n");
			}
		}
	}

	/**
	 * Obtém um valor procurado pelo usuário e busca por ele dentro da agenda.
	 * 
	 * @param resultado o array que guardará o resultado da busca
	 * @return a quantidade de contatos encontrados de acordo com o tipo de busca e
	 *         com os parâmetros fornecidos pelo usuário
	 */
	private static int buscarContato(Contato[] resultado) {
		System.out.println("-----------------------");
		System.out.println("Tipos de busca:");
		System.out.println("[1] Busca por nome");
		System.out.println("[2] Busca por telefone");
		System.out.println("Informe o tipo de busca que deseja fazer: ");
		int tipoBusca = input.nextInt();
		input.nextLine();

		int encontrados = 0;

		do {
			switch (tipoBusca) {
			/* Busca por nome */
			case 1:
				System.out.println("Insira o nome do contato a ser encontrado:");
				String nomeProcura = input.nextLine();

				for (int i = 0; i < numContatos; i++) {
					if (contatos[i].getNome().equalsIgnoreCase(nomeProcura)) {
						resultado[encontrados] = contatos[i];
						encontrados++;
					}
				}

				break;

			/* Busca por telefone */
			case 2:
				System.out.println("Insira o numero de telefone do contato a ser encontrado:");
				String telefoneProcura = input.nextLine();

				for (int i = 0; i < numContatos; i++) {
					if (contatos[i].getTelefone().equals(telefoneProcura)) {
						resultado[encontrados] = contatos[i];
						encontrados++;
					}
				}
				break;

			default:
				System.out.println("Opcao invalida!");
				encontrados = -1;
			}
		} while (encontrados < 0);

		return encontrados;
	}

	private static void listarContatos() {
		System.out.println("------------------------------");

		for (int i = 0; i < contatos.length; i++) {
			if (contatos[i] == null)
				continue;
			
			System.out.println("Contato: " + (i + 1));
			System.out.println("Nome: " + contatos[i].getNome());
			System.out.println("Telefone: " + contatos[i].getTelefone());
			System.out.println("------------------------------");
		}

	}

	private static boolean isVazia() {
		if (numContatos == 0) {
			System.out.println("A agenda esta vazia.");
			return true;
		}

		return false;
	}
}

class Contato {
	private String nome;
	private String telefone;

	public Contato(String nome, String telefone) {
		this.nome = nome;
		this.telefone = telefone;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
