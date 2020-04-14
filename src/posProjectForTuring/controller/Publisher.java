package posProjectForTuring.controller;

/**
 *
 * @author hp
 */
public interface Publisher {
    public void addSubscriber(Subscriber subscriber);
    public void change();
}
