---

# 🛒 E-commerce Test Automation Suite

An end-to-end test automation framework built to validate user journeys on a modern e-commerce website. This suite covers UI flows such as product selection, cart operations, checkout validation, and negative case handling.

---

## ✨ Features

- ✅ Page Object Model (POM) for scalable test structure
- 🛒 Full coverage of shopping workflows: add to cart, update quantities, and checkout
- 🧪 Configurable test data and reusable utilities
- ⚠️ Validations for both positive flows and expected failures (e.g., missing payment)
- 📄 Clean JavaDoc and test documentation
- 📦 Ready for CI/CD and report integration

---

## 🔧 Tech Stack

| Tool              | Role                                   |
|-------------------|----------------------------------------|
| Java              | Main programming language              |
| Selenium WebDriver| Browser automation                     |
| TestNG            | Test orchestration and assertions      |
| Maven / Gradle    | Dependency and build management        |
| GitHub Actions    | CI pipeline							 |

---

## 🚀 Getting Started

1. **Clone the repo**
   ```bash
   git clone https://github.com/koder-kanies/HomeStoreUITester.git
   cd HomeStoreUITester
   ```

2. **Set up environment**
   - Configure `TestConfig.java` with base URL
   - Optionally update test data in `UserTestData.java`

3. **Run tests**
   - Via IDE using TestNG test runner

---

## 📌 Sample Test Coverage

- Add single and multiple products to cart from homepage
- Update item quantities and validate cart totals
- Proceed to checkout with user registration
- Attempt order placement and validate error scenarios

---

## 🤝 Contributing

1. Fork the repo
2. Create your feature branch: `git checkout -b feature/my-feature`
3. Commit your changes: `git commit -m 'Add my feature'`
4. Push to the branch: `git push origin feature/my-feature`
5. Open a pull request 🚀

---

Happy testing! 💻⚙️ Need help navigating or customizing this framework? Feel free to open an issue or drop a PR 👇

---
