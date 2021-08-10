package br.gov.pi.sefaz.tasklist.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.pi.sefaz.tasklist.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
	
	// gera query automaticamente
	//Page<Task> findByCurso_Nome(String nomeCurso, Pageable pageable);  //ordem de nomes por relacionamento
	//@Query("SELECT t FROM Topico t WHERE t.curso.nome = :nomeCurso")
    //List<Topico> carregarPorNomeDoCurso(@Param("nomeCurso")(String nomeCurso);

}

