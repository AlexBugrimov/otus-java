package ru.otus.gc;

import com.sun.management.GarbageCollectionNotificationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingLong;

public class GcObserver implements Observer {

    private static final Logger logger = LoggerFactory.getLogger(GcObserver.class);
    private final List<GarbageCollectionNotificationInfo> notificationInfoList;

    public GcObserver() {
        this.notificationInfoList = new ArrayList<>();
    }

    @Override
    public Observer observe(Executor executor) {
        startMonitoring();
        executor.execute();
        return this;
    }

    @Override
    public Map<String, Long> getResults() {
        return notificationInfoList.stream().collect(
                groupingBy(GarbageCollectionNotificationInfo::getGcName,
                        summingLong(info -> info.getGcInfo().getDuration())));
    }

    private void startMonitoring() {
        for (GarbageCollectorMXBean gcBean : ManagementFactory.getGarbageCollectorMXBeans()) {
            logger.info("GC name: {}", gcBean.getName());
            NotificationEmitter emitter = (NotificationEmitter) gcBean;
            NotificationListener listener = (notification, handback) -> {
                if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                    final GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
                    notificationInfoList.add(info);
                    logger.info("Start: {}, Name: {}, Action: {}, Cause: {}, Duration: {}ms",
                            info.getGcInfo().getStartTime(),
                            info.getGcName(),
                            info.getGcAction(),
                            info.getGcCause(),
                            info.getGcInfo().getDuration());
                }
            };
            emitter.addNotificationListener(listener, null, null);
        }
    }
}
