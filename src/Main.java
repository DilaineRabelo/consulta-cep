import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner ler = new Scanner(System.in);
        int opc = 1;
        while (opc != 0) {
            System.out.println("1 - Consultar cep:");
            System.out.println("0 - Sair");
            opc = ler.nextInt();
            ler.nextLine();
            try {
                if (opc == 1) {
                    System.out.println("Digite o CEP: ");
                    String cep = ler.nextLine();
                    //cep = cep.replaceAll("\\D", "");
                    String endereco = "https://viacep.com.br/ws/" + cep + "/json/";
                    Endereco meuEndereco = null;

                    Gson gson = new GsonBuilder().setPrettyPrinting().create();

                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(endereco))
                            .build();

                    HttpResponse<String> response = client
                            .send(request, HttpResponse.BodyHandlers.ofString());

                    if (response.statusCode() == 400) {
                        throw new Exception("O cep digitado não é válido.");
                    }
                    String json = response.body();
                    CepEndereco cepEndereco = gson.fromJson(json, CepEndereco.class);
                    meuEndereco = new Endereco(cepEndereco);

                    System.out.println("O endereço é: Rua " + meuEndereco.getLogradouro() + ", " + meuEndereco.getBairro() +
                            " - " + meuEndereco.getCidade() + ", " + meuEndereco.getEstado());

                    System.out.println("Deseja salvar o endereço?");
                    System.out.println("1 - Salvar \n0 - Não");
                    int opcao = ler.nextInt();

                    if (meuEndereco != null && opcao == 1) {
                        try (FileWriter writer = new FileWriter("endereco.json")) {
                            writer.write(gson.toJson(meuEndereco));
                            System.out.println("O endereço foi salvo no arquivo 'endereco.json");
                        } catch (IOException e) {
                            System.out.println("Erro ao salvar o endereço no arquivo: " + e.getMessage());
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}