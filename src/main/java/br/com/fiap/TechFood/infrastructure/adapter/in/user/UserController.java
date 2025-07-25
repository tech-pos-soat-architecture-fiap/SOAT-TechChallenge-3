package br.com.fiap.TechFood.infrastructure.adapter.in.user;

import br.com.fiap.TechFood.application.usecases.user.User;
import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.application.port.user.UserServicePort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UserController {

    private final UserServicePort userServicePort;

    public UserController(UserServicePort userServicePort) {
        this.userServicePort = userServicePort;
    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserForm userForm) {
        User user = userServicePort.create(userForm.toUser());

        URI uri = URI.create("/users/" + user.getId());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/users")
    public ResponseEntity<PagePort<UserView>> listUser(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        PagePort<UserView> usersDTO = userServicePort.findAll(page, size).map(UserView::of);
        return ResponseEntity.ok(usersDTO);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserView> showUser(@PathVariable("id") Long id) {
        UserView userView = userServicePort.findById(id).map(UserView::of).orElse(null);
        return ResponseEntity.ok(userView);
    }

    @GetMapping("/users/cpf/{cpf}")
    public ResponseEntity<UserView> showUserByCpf(@PathVariable("cpf") String cpf) {
        UserView userView = userServicePort.findByCpf(cpf).map(UserView::of).orElse(null);
        return ResponseEntity.ok(userView);
    }
    
}
