package clienteapp.springbootclienteapp.models.service;

import clienteapp.springbootclienteapp.models.entity.Ciudad;
import clienteapp.springbootclienteapp.models.repository.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CiudadServiceImpl implements ICiudadService {

    @Autowired
    private CiudadRepository ciudadRepository;

    @Override
    public List<Ciudad> listaCiudades() {
        return (List<Ciudad>) ciudadRepository.findAll();
    }
}
