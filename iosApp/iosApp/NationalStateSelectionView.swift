import SwiftUI
import shared

struct NationalStateSelectionView: View {
    @Binding var selectedNationalState: NationState
    
    @ObservedObject private(set) var viewModel: ViewModel
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    var body: some View {
        NavigationView {
            listView()
            .navigationBarTitle("Select nation state")
        }
    }

    private func listView() -> AnyView {
        switch viewModel.nationalStates {
        case .loading:
            return AnyView(Text("Loading...").multilineTextAlignment(.center))
        case .result(let nationalStates):
            return AnyView(List(nationalStates) { nationalState in
                GenericRow(value: nationalState.name).onTapGesture {
                        selectedNationalState = nationalState
                        presentationMode.wrappedValue.dismiss()
                    }
                }
            )
        case .error(let description):
            return AnyView(Text(description).multilineTextAlignment(.center))
        }
    }
}

extension NationalStateSelectionView {
    
    enum LoadableNationaleState {
        case loading
        case result([NationState])
        case error(String)
    }

    class ViewModel: ObservableObject {
        @Published var nationalStates = LoadableNationaleState.loading
        let sdk: AirVisualSDK
        var currentCountry: Country
        
        init(sdk: AirVisualSDK, country: Country) {
            self.sdk = sdk
            self.currentCountry = country
            self.loadNationalStates()
        }

        func loadNationalStates() {
            self.nationalStates = .loading
            sdk.getStateByCountry(country: currentCountry, completionHandler: { nationalStates, error in
                if let nationalStates = nationalStates {
                    self.nationalStates = .result(nationalStates)
                } else {
                    self.nationalStates = .error(error?.localizedDescription ?? "error")
                }
            })
        }
    }
}

extension NationState: Identifiable { }
