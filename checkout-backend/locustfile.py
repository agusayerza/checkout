from locust import HttpUser, between, task, TaskSet, SequentialTaskSet
import logging
from locust import events


@events.quitting.add_listener
def _(environment, **kw):
    if environment.stats.total.fail_ratio > 0.01:
        logging.error("Test failed due to failure ratio > 1%")
        environment.process_exit_code = 1
    elif environment.stats.total.avg_response_time > 200:
        logging.error("Test failed due to average response time ratio > 200 ms")
        environment.process_exit_code = 1
    elif environment.stats.total.get_response_time_percentile(0.95) > 800:
        logging.error("Test failed due to 95th percentil response time > 800 ms")
        environment.process_exit_code = 1
    else:
        environment.process_exit_code = 0


class GetCartPriceUser(HttpUser):
    wait_time = between(0, 1)
    weight = 1

    @task
    def get_cart_price(self):
        checkout_information_for_address = {"products": [5], "address": 3}
        checkout_information_for_another_address = {"products": [5], "address": 4}

        self.client.post("/cart", json=checkout_information_for_address)
        self.client.post("/cart", json=checkout_information_for_another_address)


class GetAllCartsUser(HttpUser):
    wait_time = between(0, 1)
    weight = 6

    @task
    def get_all_carts(self):
        self.client.get("/products/")


class SequentialTasks(SequentialTaskSet):
    @task
    def add_product_to_cart(self):
        self.client.put("/users/1/cart/add/5")

    @task
    def remove_product_from_cart(self):
        self.client.put("/users/1/cart/remove/5")


class InteractWithCartUser(HttpUser):
    wait_time = between(0, 3)
    weight = 3

    tasks = [SequentialTasks]
    # @task
    # def add_then_remove_product(self):
    #     self.client.put("http://127.0.0.1:8080/users/1/cart/add/5")
    #     self.client.put("http://127.0.0.1:8080/users/1/cart/remove/5")
