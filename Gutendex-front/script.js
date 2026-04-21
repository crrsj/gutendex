const API_URL = "http://localhost:8080/api/books"; // altere se for necessário


document.addEventListener("DOMContentLoaded", () => {
  carregarLivrosSalvos();

 
  const form = document.getElementById("formBuscarPorId");
  form.addEventListener("submit", async (event) => {
    event.preventDefault();
    const id = document.getElementById("livroId").value.trim();

    if (!id) return;

    try {
      const response = await fetch(`${API_URL}/fetch/${id}`, {
        method: "POST"
      });

      if (!response.ok) {
        const erro = await response.text();
        exibirAlerta(erro, "danger");
        return;
      }

      const livro = await response.json();
      exibirLivroDetalhes(livro);
      exibirAlerta("Livro salvo com sucesso!", "success");
      carregarLivrosSalvos();
    } catch (error) {
      exibirAlerta("Erro ao conectar com a API.", "danger");
      console.error(error);
    }

    form.reset();
  });
});


async function carregarLivrosSalvos() {
  try {
    const response = await fetch(`${API_URL}`);
    const livros = await response.json();
    const tbody = document.querySelector("#tabelaLivros tbody");
    tbody.innerHTML = "";

    livros.forEach(livro => {
      const tr = document.createElement("tr");
      tr.innerHTML = `
        <td>${livro.id}</td>
        <td>${livro.title}</td>
        <td>${livro.author}</td>
        <td>${livro.version}</td>
        <td>
          <a href="${livro.downloadUrl}" class="btn btn-sm btn-outline-primary" target="_blank">
            Baixar
          </a>
        </td>
      `;
      tbody.appendChild(tr);
    });
  } catch (error) {
    console.error("Erro ao carregar livros:", error);
    exibirAlerta("Erro ao carregar os livros salvos.", "danger");
  }
}


function exibirLivroDetalhes(livro) {
  const div = document.getElementById("livroDetalhes");
  div.innerHTML = `
    <div class="card">
      <div class="card-body">
        <h5 class="card-title">${livro.title}</h5>
        <h6 class="card-subtitle mb-2 text-muted">${livro.author}</h6>
        <p><strong>ID:</strong> ${livro.id} | <strong>Versão:</strong> ${livro.version}</p>
        <a href="${livro.downloadUrl}" target="_blank" class="btn btn-sm btn-primary">Baixar EPUB</a>
      </div>
    </div>
  `;
}


function exibirAlerta(mensagem, tipo) {
  const alertaDiv = document.getElementById("alerta");
  alertaDiv.innerHTML = `
    <div class="alert alert-${tipo}" role="alert">
      ${mensagem}
    </div>
  `;

  setTimeout(() => {
    alertaDiv.innerHTML = "";
  }, 4000);
}
