import UIKit
import SwiftUI
import ComposeApp

@main
struct iosApp: App {
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}

struct ContentView: View {
    @State private var colorScheme: ColorScheme = .dark
    var body: some View {
        ComposeView(setScheme: { scheme in colorScheme = scheme })
            .ignoresSafeArea(.all)
            .preferredColorScheme(colorScheme)
    }
}

struct ComposeView: UIViewControllerRepresentable {

    var setScheme: (ColorScheme) -> Void
    init(setScheme: @escaping (ColorScheme) -> Void) {
        self.setScheme = setScheme
    }

    func makeUIViewController(context: Context) -> UIViewController {
            MainKt.MainViewController { isLight in
                setScheme(isLight.boolValue ? .light : .dark)
            }
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}
