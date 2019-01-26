

# Continuous Integration mit GitLab

# Agenda

## Block1 Grundlagen CI/CD

 * CI
   * Git-Flow
   * Merge Hell
 * Workflow: Gemeinsam auf dem master entwickeln

 * CI/CD

## Block2 Using Gitlab for CI/CD


## Block3 Building Workflows for CI/CD

# Material

Ich demonstriere anhand einiger Beispiele, wie CI/CD mit GitLab implementiert werden kann. Ich berichte von CI/CD-Lösungen, die ich aus der Praxis kenne. Dann diskutieren Strategien, wie das bei Rohde und Schwarz umgesetzt werden kann. Im praktischen Teil werden die Beispiele angepasst und erweitert.

## Warum?
 * Matzes 70 Schritte auf 1

 * Cycle Times -- Dave Farley
   * Flow:
     - Weniger Unterbrechungen f, Entwickler
     - kein Q/A Bottleneck
   * Schnelleres Feedback, Experimente
 * Kleinere
   * Einfachere Konflikte (horror of Release)
   * Schnellere Fehlersuche
 * Code Quality/One Version

## Was?

 * CI
    * Master
    * Build system
    * Build Pipeline
    * Reviews Quality Curve

lead time, deployment frequency, mean time to restore (MTTR), and change fail percentage.

 * CD
    * Deployment
    * Monitoring
 * Artefaktrepository
   * oder Docker image registry
 * Gitlab Runner
 * Gitlab Job + yml
 * Feature Toggling

## Wie?

 * Trunk based Development
   - worst way to use branches
   - zeromq way
 * When to merge
 * Auto Devops
 * Tests Unit/Modul/Integrationstests
   - Geschwindigkeit/Parallelisierung
 * Abhängigkeiten/Modularisierung



## Wohin noch?

 * Release-Doku
 * Infrastructure as Code
 * Kubernetes
 * monorepo
