import SwiftUI
import shared

@main
struct iOSApp: App {
    let sdk = AirVisualSDK()
	var body: some Scene {
		WindowGroup {
            ContentView(viewModel: .init(sdk: sdk))
		}
	}
}
