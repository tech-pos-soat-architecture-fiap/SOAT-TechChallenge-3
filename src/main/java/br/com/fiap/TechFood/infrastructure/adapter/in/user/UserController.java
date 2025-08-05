package br.com.fiap.TechFood.infrastructure.adapter.in.user;

import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.application.port.user.CreateUserPort;
import br.com.fiap.TechFood.application.port.user.FindAllUsersPort;
import br.com.fiap.TechFood.application.port.user.FindUserByCpfPort;
import br.com.fiap.TechFood.application.port.user.FindUserByIdPort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UserController {

    private final CreateUserPort createUserPort;
    private final FindAllUsersPort findAllUsersPort;
    private final FindUserByIdPort findUserByIdPort;
    private final FindUserByCpfPort findUserByCpfPort;

    public UserController(CreateUserPort createUserPort,
                          FindAllUsersPort findAllUsersPort,
                          FindUserByIdPort findUserByIdPort,
                          FindUserByCpfPort findUserByCpfPort) {
        this.createUserPort = createUserPort;
        this.findAllUsersPort = findAllUsersPort;
        this.findUserByIdPort = findUserByIdPort;
        this.findUserByCpfPort = findUserByCpfPort;
    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserForm userForm) {
        var user = createUserPort.createUser(userForm.name(), userForm.email(), userForm.cpf());
        URI uri = URI.create("/users/" + user.id());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/users")
    public ResponseEntity<PagePort<UserView>> listUser(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        PagePort<UserView> usersDTO = findAllUsersPort.findAll(page, size);
        return ResponseEntity.ok(usersDTO);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserView> showUser(@PathVariable("id") Long id) {
        return findUserByIdPort.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/users/cpf/{cpf}")
    public ResponseEntity<UserView> showUserByCpf(@PathVariable("cpf") String cpf) {
        return findUserByCpfPort.findByCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
