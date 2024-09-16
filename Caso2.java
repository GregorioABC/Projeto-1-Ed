// lista encadeada simples
import java.util.Scanner;

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
    Evento evento;
    Participante proximo; // aponta para o próximo participante

    public Participante(String nome, int numeroInscricao, Evento evento) {
        this.nome = nome;
        this.numeroInscricao = numeroInscricao;
        this.evento = evento;
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
            participante.proximo = evento.participantes;
            evento.participantes = participante;
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
        return null; // retorna quando o evento não é encotrado
    }
    public Participante buscarParticipante(String nomeEvento, int numeroInscricao) {
        Evento evento = buscarEvento(nomeEvento);
        if (evento != null) {
            Participante atual = evento.participantes;
            while (atual != null) {
                if (atual.numeroInscricao == numeroInscricao) {
                    return atual;
                }
                atual = atual.proximo;
            }
        }
        return null; // retorna quando um participante não é encotrado
    }

    // atualiza/muda informações necessárias
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

