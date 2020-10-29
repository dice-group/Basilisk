from pathlib import Path


class TripleStore:

    @property
    def name(self) -> str:
        pass

    @property
    def query_file(self) -> Path:
        # TODO: support also downloading files beforehand
        return Path(self._queries_file_path)

    @property
    def rdf_file(self) -> Path:
        # TODO: support also downloading files beforehand
        return Path(self._rdf_file_path)
