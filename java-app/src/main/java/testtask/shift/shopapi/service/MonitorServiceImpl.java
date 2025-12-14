package testtask.shift.shopapi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testtask.shift.shopapi.model.monitor.Monitor;
import testtask.shift.shopapi.repository.MonitorRepository;

@Service
@Transactional
public class MonitorServiceImpl
        extends BaseCrudService<Monitor>
        implements MonitorService {

    public MonitorServiceImpl(MonitorRepository repository) {
        super(repository, "Monitor");
    }

    @Override
    public Iterable<Monitor> getAllMonitors() {
        return findAll();
    }

    @Override
    public Monitor getMonitor(long id) {
        return findById(id);
    }
}
