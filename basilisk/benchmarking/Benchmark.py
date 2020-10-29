from pathlib import Path


class Benchmark:
    def __init__(self, name: str, queries_file_path: str, rdf_file_path: str):
        self._name: str = name
        self._queries_file_path: str = queries_file_path
        self._rdf_file_path: str = rdf_file_path

        @property
        def name(self) -> str:
            return self._name

        @property
        def query_file(self) -> Path:
            # TODO: support also downloading files beforehand
            return Path(self._queries_file_path)

        @property
        def rdf_file(self) -> Path:
            # TODO: support also downloading files beforehand
            return Path(self._rdf_file_path)
