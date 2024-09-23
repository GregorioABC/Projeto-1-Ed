import java.util.Scanner;

public class SistemaEventos {
    class Evento {
        String nome;
        String data;
        String local;
        int capacidade;
        Participante participantes; // Lista encadeada de participantes

        public Evento(String nome, String data, String local, int capacidade) {
            this.nome = nome;
            this.data = data;
            this.local = local;
            this.capacidade = capacidade;
            this.participantes = null;
        }
    }

    class Participante {
        String nome;
        int numeroInscricao;
        Participante proximo; // aponta para o próximo participante

        public Participante(String nome, int numeroInscricao) {
            this.nome = nome;
            this.numeroInscricao = numeroInscricao;
            this.proximo = null;
        }
    }

    class NodoEvento {
        Evento evento;
        NodoEvento proximo; // aponta para o próximo evento na lista

        public NodoEvento(Evento evento) {
            this.evento = evento;
            this.proximo = null;
        }
    }

    class ListaEncadeada {
        private NodoEvento cabeca; // Cabeça da lista de eventos

        public ListaEncadeada() {
            this.cabeca = null;
        }

        // funções para inserir
        public void adicionarEventoInicio(Evento evento) {
            NodoEvento novoEvento = new NodoEvento(evento);
            novoEvento.proximo = cabeca;
            cabeca = novoEvento;
        }

        public void adicionarEventoFim(Evento evento) {
            NodoEvento novoEvento = new NodoEvento(evento);
            if (cabeca == null) {
                cabeca = novoEvento;
            } else {
                NodoEvento atual = cabeca;
                while (atual.proximo != null) {
                    atual = atual.proximo;
                }
                atual.proximo = novoEvento;
            }
        }

        public void adicionarParticipante(String nomeEvento, Participante participante) {
            Evento evento = buscarEvento(nomeEvento);
            if (evento != null) {
                int totalParticipantes = 0;
                Participante atual = evento.participantes;
                while (atual != null) {
                    totalParticipantes++;
                    atual = atual.proximo;
                }
                if (totalParticipantes < evento.capacidade) {
                    participante.proximo = evento.participantes;
                    evento.participantes = participante;
                    System.out.println("Participante " + participante.nome + " adicionado ao evento " + nomeEvento + ".");
                } else {
                    System.out.println("Capacidade máxima do evento atingida.");
                }
            } else {
                System.out.println("Evento não encontrado!");
            }
        }
        

        // funções para remover
        public void removerEvento(String nomeEvento) {
            NodoEvento atual = cabeca;
            NodoEvento anterior = null;
            while (atual != null) {
                if (atual.evento.nome.equals(nomeEvento)) {
                    if (anterior != null) {
                        anterior.proximo = atual.proximo;
                    } else {
                        cabeca = atual.proximo;
                    }
                    return;
                }
                anterior = atual;
                atual = atual.proximo;
            }
            System.out.println("Esse evento não existe.");
        }

        public void removerParticipante(String nomeEvento, int numeroInscricao) {
            Evento evento = buscarEvento(nomeEvento);
            if (evento != null) {
                Participante atual = evento.participantes;
                Participante anterior = null;
                while (atual != null) {
                    if (atual.numeroInscricao == numeroInscricao) {
                        if (anterior != null) {
                            anterior.proximo = atual.proximo;
                        } else {
                            evento.participantes = atual.proximo;
                        }
                        return;
                    }
                    anterior = atual;
                    atual = atual.proximo;
                }
                System.out.println("Participante não encontrado.");
            } else {
                System.out.println("Evento não encontrado.");
            }
        }

        // funções de busca
        public Evento buscarEvento(String nomeEvento) {
            NodoEvento atual = cabeca;
            while (atual != null) {
                if (atual.evento.nome.equals(nomeEvento)) {
                    return atual.evento;
                }
                atual = atual.proximo;
            }
            return null; // retorna quando o evento não é encontrado
        }

        public void atualizarNomeEvento(String nomeAntigo, String novoNome) {
            Evento evento = buscarEvento(nomeAntigo);
            if (evento != null) {
                evento.nome = novoNome;
            } else {
                System.out.println("Evento não encontrado.");
            }
        }

        public void atualizarDataEvento(String nomeEvento, String novaData) {
            Evento evento = buscarEvento(nomeEvento);
            if (evento != null) {
                evento.data = novaData;
            } else {
                System.out.println("Evento não encontrado.");
            }
        }

        public void atualizarLocalEvento(String nomeEvento, String novoLocal) {
            Evento evento = buscarEvento(nomeEvento);
            if (evento != null) {
                evento.local = novoLocal;
            } else {
                System.out.println("Evento não encontrado.");
            }
        }

        public void atualizarCapacidadeEvento(String nomeEvento, int novaCapacidade) {
            Evento evento = buscarEvento(nomeEvento);
            if (evento != null) {
                evento.capacidade = novaCapacidade;
            } else {
                System.out.println("Evento não encontrado.");
            }
        }

        public void imprimirEventosEParticipantes() {
            NodoEvento atual = cabeca;
            while (atual != null) {
                Evento evento = atual.evento;
                System.out.println("Evento: " + evento.nome);
                System.out.println("Data: " + evento.data);
                System.out.println("Local: " + evento.local);
                System.out.println("Capacidade: " + evento.capacidade);

                Participante participanteAtual = evento.participantes;
                if (participanteAtual != null) {
                    System.out.println("Participantes:");
                    while (participanteAtual != null) {
                        System.out.println("Nome: " + participanteAtual.nome + ", Inscrição: " + participanteAtual.numeroInscricao);
                        participanteAtual = participanteAtual.proximo;
                    }
                } else {
                    System.out.println("Nenhum participante registrado.");
                }

                System.out.println("----------------------");
                atual = atual.proximo;
            }
        }
    }

    public static void executar(Scanner scanner) { 
        SistemaEventos sistema = new SistemaEventos();
        ListaEncadeada listaEventos = sistema.new ListaEncadeada();
        int opcao;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar evento no início");
            System.out.println("2. Adicionar evento no final");
            System.out.println("3. Adicionar participante em um evento");
            System.out.println("4. Remover evento");
            System.out.println("5. Remover participante");
            System.out.println("6. Atualizar nome do evento");
            System.out.println("7. Atualizar data do evento");
            System.out.println("8. Atualizar local do evento");
            System.out.println("9. Atualizar capacidade do evento");
            System.out.println("10. Exibir eventos e participantes");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do evento: ");
                    String nomeEventoInicio = scanner.nextLine();
                    System.out.print("Digite a data do evento: ");
                    String dataInicio = scanner.nextLine();
                    System.out.print("Digite o local do evento: ");
                    String localInicio = scanner.nextLine();
                    System.out.print("Digite a capacidade do evento: ");
                    int capacidadeInicio = scanner.nextInt();
                    scanner.nextLine(); // Consumir a quebra de linha

                    Evento eventoInicio = sistema.new Evento(nomeEventoInicio, dataInicio, localInicio, capacidadeInicio);
                    listaEventos.adicionarEventoInicio(eventoInicio);
                    System.out.println("Evento adicionado no início.");
                    break;

                case 2:
                    System.out.print("Digite o nome do evento: ");
                    String nomeEventoFim = scanner.nextLine();
                    System.out.print("Digite a data do evento: ");
                    String dataFim = scanner.nextLine();
                    System.out.print("Digite o local do evento: ");
                    String localFim = scanner.nextLine();
                    System.out.print("Digite a capacidade do evento: ");
                    int capacidadeFim = scanner.nextInt();
                    scanner.nextLine(); // Consumir a quebra de linha

                    Evento eventoFim = sistema.new Evento(nomeEventoFim, dataFim, localFim, capacidadeFim);
                    listaEventos.adicionarEventoFim(eventoFim);
                    System.out.println("Evento adicionado no final.");
                    break;

                case 3:
                    System.out.print("Digite o nome do evento: ");
                    String nomeEventoParticipante = scanner.nextLine();
                    System.out.print("Digite o nome do participante: ");
                    String nomeParticipante = scanner.nextLine();
                    System.out.print("Digite o número de inscrição: ");
                    int numeroInscricao = scanner.nextInt();
                    scanner.nextLine(); // Consumir a quebra de linha

                    Participante participante = sistema.new Participante(nomeParticipante, numeroInscricao);
                    listaEventos.adicionarParticipante(nomeEventoParticipante, participante);
                    break;

                case 4:
                    System.out.print("Digite o nome do evento a ser removido: ");
                    String eventoRemover = scanner.nextLine();
                    listaEventos.removerEvento(eventoRemover);
                    break;

                case 5:
                    System.out.print("Digite o nome do evento: ");
                    String eventoParticipanteRemover = scanner.nextLine();
                    System.out.print("Digite o número de inscrição do participante a ser removido: ");
                    int inscricaoRemover = scanner.nextInt();
                    scanner.nextLine(); // Consumir a quebra de linha

                    listaEventos.removerParticipante(eventoParticipanteRemover, inscricaoRemover);
                    break;

                case 6:
                    System.out.print("Digite o nome do evento a ser atualizado: ");
                    String nomeAntigo = scanner.nextLine();
                    System.out.print("Digite o novo nome do evento: ");
                    String novoNome = scanner.nextLine();
                    listaEventos.atualizarNomeEvento(nomeAntigo, novoNome);
                    break;

                case 7:
                    System.out.print("Digite o nome do evento a ser atualizado: ");
                    String nomeEventoData = scanner.nextLine();
                    System.out.print("Digite a nova data do evento: ");
                    String novaData = scanner.nextLine();
                    listaEventos.atualizarDataEvento(nomeEventoData, novaData);
                    break;

                case 8:
                    System.out.print("Digite o nome do evento a ser atualizado: ");
                    String nomeEventoLocal = scanner.nextLine();
                    System.out.print("Digite o novo local do evento: ");
                    String novoLocal = scanner.nextLine();
                    listaEventos.atualizarLocalEvento(nomeEventoLocal, novoLocal);
                    break;

                case 9:
                    System.out.print("Digite o nome do evento a ser atualizado: ");
                    String nomeEventoCapacidade = scanner.nextLine();
                    System.out.print("Digite a nova capacidade do evento: ");
                    int novaCapacidade = scanner.nextInt();
                    scanner.nextLine(); // Consumir a quebra de linha
                    listaEventos.atualizarCapacidadeEvento(nomeEventoCapacidade, novaCapacidade);
                    break;

                case 10:
                    listaEventos.imprimirEventosEParticipantes();
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }
}
