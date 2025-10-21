FROM postgres:17.2-alpine

# On évite d'écrire des secrets dans l'image.
# Les variables POSTGRES_DB/USER/PASSWORD seront passées au "docker run" (étape suivante).

# Copie des scripts d'initialisation (exécutés au premier démarrage du datadir)
COPY initdb/ /docker-entrypoint-initdb.d/
