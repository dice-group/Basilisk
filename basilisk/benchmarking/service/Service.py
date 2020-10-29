from queue import Queue

import docker

from basilisk.benchmarking.service.Task import Task


class Service:

    def __init__(self):
        self._task_queue: Queue = Queue()

    def submit_task(self, task: Task):
        self._task_queue.put_nowait(task)

    def execute_task(self, task: Task):
        client = docker.from_env()
        client.containers.list(all=True)
        pass


if __name__ == "__main__":
    Service().execute_task(Task())
