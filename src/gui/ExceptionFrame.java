package gui;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import util.GBC;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author moerie
 */
public class ExceptionFrame extends JFrame {

    private Exception e;
    private List<Throwable> exceptions;

    private JTextArea message;
    private JTextArea stackTrace;

    public ExceptionFrame(Exception e) {
        this.e = e;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(800,600));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        fillContent();
    }

    private void fillContent() {
        ScheduledExecutorService scheduler =
                Executors.newScheduledThreadPool(1);

        Runnable contentFill = new Runnable() {
            @Override
            public void run() {
                ExceptionFrame.this.fillExceptionStack();
                ExceptionFrame.this.createComponents();
                ExceptionFrame.this.addComponents();
                ExceptionFrame.this.configureComponents();
                ExceptionFrame.this.repaint();
                ExceptionFrame.this.revalidate();
            }
        };
        scheduler.schedule(contentFill, 100, TimeUnit.MILLISECONDS);

    }

    private void fillExceptionStack() {
        exceptions = new LinkedList<>();
        Throwable exception = e;
        while(exception != null) {
            exceptions.add(exception);
            exception = exception.getCause();
        }
    }

    private void createComponents() {
        this.setTitle("Error");
        String separator = "\n" + getExceptionSeparator() + "\n\n";
        List<String> exceptionsAsString = Lists.transform(exceptions, new Function<Throwable, String>() {
            @Override
            public String apply(Throwable e) {
                String title = e.getClass().getSimpleName();
                String message = e.getMessage();
                return String.format("%s\n%s\n%s", title, getTitleLine(title), message);
            }
        });
        message = new JTextArea(Joiner.on(separator).join(exceptionsAsString));

        stackTrace = new JTextArea(Throwables.getStackTraceAsString(e));
    }

    private void addComponents() {
        this.setLayout(new GridBagLayout());
        this.add(new JScrollPane(message), new GBC(0,0).setWeight(1, 1).setFill(GBC.BOTH).setInsets(5));
        this.add(new JScrollPane(stackTrace), new GBC(0,1).setWeight(1, 1).setFill(GBC.BOTH).setInsets(5));
    }

    private void configureComponents() {
        stackTrace.setForeground(Color.RED);
        Insets margin = new Insets(10,10,10,10);
        message.setMargin(margin);
        stackTrace.setMargin(margin);
        message.setEditable(false);
        stackTrace.setEditable(false);
    }

    private String getTitleLine(String title) {
        return Strings.repeat("-", title.length()*2);
    }

    private String getExceptionSeparator() {
        return Strings.repeat("_", this.getWidth()/9);
    }
}
