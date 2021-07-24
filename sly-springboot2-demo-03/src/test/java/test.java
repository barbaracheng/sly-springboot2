import com.sly.water.entities.Worker;
import com.sly.water.service.WorkerService;
import com.sly.water.service.impl.WorkerServiceImpl;
import org.junit.Test;

import java.util.List;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/23 20:12
 */
public class test {

    private WorkerService workerService = new WorkerServiceImpl();

    @Test
    public void test() {
        List<Worker> worker = workerService.searchWorker("张三");
        List<Worker> workers = workerService.listWorker();
        for (Worker w : workers) {
            System.out.println(w);
        }
        for (Worker worker1 : worker) {
            System.out.println(worker1);
        }

    }
}
